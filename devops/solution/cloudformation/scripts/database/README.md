# Database Admin Script
## Connect to RDS using IAM Account
### Local AWS Profile Setup
To be able to connect to RDS using IAM user, you need to install `aws-cli` locally and setup a profile like this in the local credentials file:
```
[profile]
region=${AWS_DEFAULT_REGION}
aws_access_key_id=<YOUR_IAM_ACCESS_KEY>
aws_secret_access_key=<YOUR_IAM_SECRET_ACCESS_KEY>
```

### Using the Scrips
Once you have the AWS profile you can run the following scripts to connect to staging and production environment using following scripts:
* staging (see [connect_to_sandbox_db_using_iam.sh](connect_to_sandbox_db_using_iam.sh)):

`sh connect_to_sandbox_db_using_iam.sh`

* production (see [connect_to_PROD_db_using_iam.sh](connect_to_PROD_db_using_iam.sh))

`sh connect_to_PROD_db_using_iam.sh`

### After Connection
The connection command does not set a default database. You can use `\l` to list the databases and `\c` to connect to desired database.

### Related Doc
https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/UsingWithRDS.IAMDBAuth.Connecting.AWSCLI.PostgreSQL.html
