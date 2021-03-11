#!/usr/bin/env bash
echo "Copy the Lambda to the S3 bucket"
aws s3 cp serverless-query-dev.zip s3://lambdas
echo "Create the Lambda with Cloudformation"
aws cloudformation create-stack \
--stack-name serverless-query-cfn-dev \
--template-body file://serverless-cfn.json \
--region ${AWS_DEFAULT_REGION} \
--capabilities CAPABILITY_NAMED_IAM \
--parameters '[
   {"ParameterKey":"PEndpoint","ParameterValue":"dev.ctpaqyvmjqcv.eu-west-2.rds.amazonaws.com"},
   {"ParameterKey":"PPort","ParameterValue":"5432"},
   {"ParameterKey":"PDatabase","ParameterValue":"database"},
   {"ParameterKey":"PDbUser","ParameterValue":"username"},
   {"ParameterKey":"PDbPassword","ParameterValue":"password"},
   {"ParameterKey":"PS3Bucket","ParameterValue":"lambdas"},
   {"ParameterKey":"PSubnetIds","ParameterValue":"SUBNET-ID"},
   {"ParameterKey":"PSecurityGroupIds","ParameterValue":"sg-ID"},
   {"ParameterKey":"FuncName","ParameterValue":"ServerlessQuery-dev"}
 ]'
