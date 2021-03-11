DB_HOST="sandbox.cepug3e1vme6.us-east-2.rds.amazonaws.com"
DB_USER="admin"
AWS_PROFILE="profile"
DB_PASS=$(aws --profile $AWS_PROFILE rds generate-db-auth-token --hostname $DB_HOST --port 5432 --username $DB_USER)
psql "host=$DB_HOST port=5432 sslmode=prefer user=$DB_USER password=$DB_PASS"
