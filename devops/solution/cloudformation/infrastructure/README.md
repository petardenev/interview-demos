# infrastructure

 **AWS Environment**
  * 00-infrastructure-core.yaml - Provisions VPC, PrivateSubnets, PublicSubnets, NATGateways
  * 01-acm-route53-auto-appover-lambda.yaml - Lambda to create Route 53 resources for automatic ACM approval
  * 02-infrastructure-application.yaml - Creates the Autoscalable ECS Cluster for the particular application to be hosted in, Load Balancer, Security Groups and Launch Configuration,for later re-use in Autoscaling
  * 03-deployment.yaml - This is the actual deployment, that same script is available in each of the service reporisoties in the aws/ directory
  * cf-helper-ELBv2-rule-priority.yml - Cloudformation helper that gets the next available rule priority from an ELBv2 listener
  * task-execution-assume-role.json - IAM role for ECS
