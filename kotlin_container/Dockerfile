FROM mcr.microsoft.com/devcontainers/base:ubuntu-22.04

ENV VERSION_GRADLE=8.14.2
ENV VERSION_KOTLIN=2.0.10

# Install java-21
RUN apt update && \
apt install openjdk-21-jdk -y

# appuserユーザーを作成し、作業ディレクトリの権限を変更
RUN mkdir /workspace
RUN useradd -m appuser && chown -R appuser:appuser /workspace

# 作業ディレクトリ
WORKDIR /workspace

USER appuser

# Install SDKMan
RUN curl -s https://get.sdkman.io | bash

# Install Gradle
RUN bash -c " \
    source \"$HOME/.sdkman/bin/sdkman-init.sh\" && \
    sdk install gradle $VERSION_GRADLE"

# Install Kotlin
RUN bash -c " \
    source \"$HOME/.sdkman/bin/sdkman-init.sh\" && \
    sdk install kotlin $VERSION_KOTLIN"

CMD ["gradle", "--version"]
