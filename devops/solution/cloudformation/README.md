# Cloudformation Infrastructure and Deployment Automation Repository

The repository contains all of the cloudformation templates needed to provision
a working environment, e.g. staging, production, development, etc.

The deployment logic is Blue-Green, based on the health of the newly provisioned container, if its healthy for 30 seconds and does have a valid Healthcheck endpoint - old container is shutdown and traffic is forwarded to the new instance.
Architecture used does provide one Application Load Balancer for each Service, to ensure that the service would autoscale without any problems. Autoscaling policy is currently not configured
The Blue/Green Deployment logic is integrated in each deploy template hosted in the aws directory of each software service.

## What is hosted in this repository?
#### **infrastructure**
Contains the cloudformation infrastructure stacks
#### **rds**
AWS Lambda function + Cloudformation template for restoring PostgreSQL RDS instance from Snapshot ( for example restore PROD database to Sandbox and Dev instances) and execute ETL process(this is actually what the Lambda is for)
### **scripts**
Contains an ability for the developers to access the PostgreSQL Instance in both Sandbox and Production using AWS IAM 

### FAQ on troubleshooting deployments

* In case, the deployment fails -  cloudformation automatically reverts the previous version

* You can check the cloudwatch logs, if the deployment had failed - the previous log listed in cloudwatch would be the problem one

* How to access the ECS Cluster via ssh web console ( e.g. ssh via web console) https://aws.amazon.com/blogs/aws/new-session-manager/
