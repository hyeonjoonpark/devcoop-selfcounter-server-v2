FROM ubuntu:latest
LABEL authors="hyeonjoonpark"

ENTRYPOINT ["top", "-b"]