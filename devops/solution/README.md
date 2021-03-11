
# Brief Description

This is a [template repository](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-template-repository) for Java Spring-Boot based application,
allowing the developers to have boilerplate code and directory structure across projects easily.
The idea is similar to [boilr](https://github.com/tmrts/boilr) and [cookiecutter](https://drivendata.github.io/cookiecutter-data-science/)
The repository contains all of the items needed for a developer to actually start the working process with out-of-the-box implementation of Continuous Integration, Continuous Deployment that includes building Docker Container, applying the [database changesets](https://www.liquibase.org/) and deployment of the actual Java Service to [AWS ECS](https://aws.amazon.com/ecs/features/) using [Cloudformation](https://aws.amazon.com/cloudformation/features/) and [Bitbucket Pipelines](https://bitbucket.org/product/features/pipelines)

## Directory Structure

 - java-microservice-template - the template repository, which includes full CI/CD implementation(build and deploy docker container and execute database changesets) done with Bitbucket Pipelines

 - cloudformation - the repository contains cloudformation templates for the whole infrastructure

## More items in the directories

  - README.md files available in most of the directories, explaining the purpose of each
  - QuickBase_DevOps_Presentation.pdf is the actual presentation requested

## java-microservice-template

This is a fully functional Java 11, SpringBoot + OpenAPI service working with Postgresql DB engine, the implementation also has tests that use H2 Database. The service can be compiled locally running `cd server; mvn clean package` or with docker executing ` docker build . -t template:latest` to verify the multistage build process
Please keep in mind, if you build the docker container, you must add the DB credentials upon running the container, e.g. `docker run -e DBHOST=${DBHOST} -e DBNAME=${DBNAME} -e DBUSER=${DBUSER} -e DBPASSWORD=${DBPASSWORD} -it template:latest` otherwise the service won't start
