#!/usr/bin/env bash
set -x
### Restores latest RDS snapshot from PROD to DEV/Sandbox cluster
template_yml=$1
stack_type=$2
db_cluster_name=$3
#### Obtain latest snapshot
D4T3=$(date +"%Y-%m-%d")
SNAPSHOT=`aws rds describe-db-cluster-snapshots --db-cluster-identifier postgre-live-cluster --region ${AWS_DEFAULT_REGION} --snapshot-type automated --query "DBClusterSnapshots[?SnapshotCreateTime<'${D4T3}'].DBClusterSnapshotIdentifier"|  tr -d '[:blank:]' | grep rds | tail -1`

aws cloudformation delete-stack --stack-name restore-${stack_type} --region ${AWS_DEFAULT_REGION}
aws cloudformation wait stack-delete-complete --stack-name restore-${stack_type} --region ${AWS_DEFAULT_REGION}
aws cloudformation create-stack --stack-name restore-${stack_type} --template-body file://${template_yml} --parameters ParameterKey=DBSnapshot,ParameterValue=${SNAPSHOT} ParameterKey=DBCluster,ParameterValue=${db_cluster_name} ParameterKey=DBInstance,ParameterValue=`echo $db_cluster_name|sed 's/\-cluster//g'` --region ${AWS_DEFAULT_REGION}
aws cloudformation wait stack-create-complete --stack-name restore-${stack_type} --region ${AWS_DEFAULT_REGION}
aws lambda invoke --function-name ServerlessQuery-${stack_type} --region ${AWS_DEFAULT_REGION} --log-type Tail invoke-lambda.out
if [[ "${stack_type}" == "dev" ]]; then
  aws rds modify-db-cluster --db-cluster-identifier postgre-dev-cluster --master-user-password ${DEVDBPASSWORD} --apply-immediately --region ${AWS_DEFAULT_REGION}
elif [[ "${stack_type}" == "sandbox" ]]; then
  aws rds modify-db-cluster --db-cluster-identifier postgre-sandbox-cluster --master-user-password ${SANDBOXDBPASSWORD} --apply-immediately --region ${AWS_DEFAULT_REGION}
fi
