# downloaded image
FROM bellsoft/liberica-openjdk-alpine:latest-cds
# install tools
RUN apk add curl jq
# workspace
WORKDIR /home/selenium-docker
# add the required files for project to workspace
ADD target/docker-resources ./
ADD runner.sh               runner.sh

# run the compiled tests
ENTRYPOINT sh runner.sh