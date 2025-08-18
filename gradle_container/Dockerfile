# ベースイメージとして Temurin OpenJDK 21 を使用
FROM eclipse-temurin:21-jdk-jammy

# アプリケーションの作業ディレクトリを設定
WORKDIR /app

# Gradle のインストール
# Gradle Wrapper のセットアップに使用
RUN apt-get update && apt-get install -y gradle

# コンテナが起動したときに何も実行せず、ユーザー入力を待機する
# これにより、ユーザーがコンテナ内で手動でコマンドを実行できる
CMD ["/bin/bash"]
