FROM openjdk:21-slim-bookworm

# Kotlinのバージョンを指定
ENV KOTLIN_VERSION=2.2.0
ENV GRADLE_VERSION=8.14.2

# 必要なツールをインストールし、KotlinコンパイラとGradleをセットアップ
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget unzip git vim openssh-client \
    # Kotlin
    && wget -q https://github.com/JetBrains/kotlin/releases/download/v$KOTLIN_VERSION/kotlin-compiler-$KOTLIN_VERSION.zip \
    && unzip kotlin-compiler-$KOTLIN_VERSION.zip -d /opt \
    && rm kotlin-compiler-$KOTLIN_VERSION.zip \
    && ln -s /opt/kotlinc/bin/* /usr/local/bin/ \
    # Gradle
    && wget -q https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip \
    && unzip gradle-$GRADLE_VERSION-bin.zip -d /opt \
    && rm gradle-$GRADLE_VERSION-bin.zip \
    && ln -s /opt/gradle-$GRADLE_VERSION/bin/gradle /usr/local/bin/gradle \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# 作業ディレクトリ
WORKDIR /workspace

# デフォルトのコマンド
# appuserユーザーを作成し、作業ディレクトリの権限を変更
RUN useradd -m appuser && chown -R appuser:appuser /workspace

# Install SQLite3
RUN apt-get update && apt-get install -y sqlite3 libsqlite3-dev

USER appuser

CMD ["gradle", "--version"]
