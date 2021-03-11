#!/usr/bin/env bash

set -e
set -x

if [[ "$#" -ne 5 ]]; then
    echo "Illegal number of parameters"
    echo "Usage: update-stack.sh stack_name env_name template_file version service_name"
    exit 1
fi

stack_name=$1
env_name=$2
template_file=$3
version=$4
service_name=$5

aws cloudformation update-stack \
    --stack-name=${stack_name} \
    --capabilities CAPABILITY_IAM \
    --template-body=file://${template_file} \
    --parameters \
        ParameterKey=DBHOST,ParameterValue=${DBHOST} \
        ParameterKey=DBPASSWORD,ParameterValue=${DBPASSWORD} \
        ParameterKey=DBNAME,ParameterValue=${DBNAME} \
        ParameterKey=EnvironmentName,ParameterValue=${env_name} \
        ParameterKey=InfrastructureStackName,UsePreviousValue=true \
        ParameterKey=LogsRetention,UsePreviousValue=true \
        ParameterKey=ServiceImage,ParameterValue=${AWS_ACCOUNT_ID}.dkr.ecr.us-east-2.amazonaws.com/${service_name}	\
        ParameterKey=ServiceVersion,ParameterValue=${version} \
        ParameterKey=ServiceName,ParameterValue=${service_name} \
        ParameterKey=LogGroupName,ParameterValue=/${service_name}/docker/${env_name}
aws cloudformation wait stack-update-complete --stack-name=${stack_name}
