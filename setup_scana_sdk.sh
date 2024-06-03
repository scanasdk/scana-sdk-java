#!/bin/bash

# 设置变量
VERSION="3.0"
TAR_FILE="scana-sdk.tar.gz"
URL="https://github.com/scanasdk/scana-sdk-java/releases/download/v${VERSION}/${TAR_FILE}"
DOWNLOAD_DIR="lib"
JAR_FILE="scana-sdk-java.jar"
GROUP_ID="com.newkms.qixincha"
ARTIFACT_ID="scana-sdk-java"


# 创建下载目录
mkdir -p $DOWNLOAD_DIR

# 下载tar.gz文件
echo "Downloading $URL..."
curl -L -o $DOWNLOAD_DIR/$TAR_FILE $URL

# 解压tar.gz文件
echo "Extracting $TAR_FILE..."
tar -xzvf $DOWNLOAD_DIR/$TAR_FILE -C $DOWNLOAD_DIR

# 安装JAR文件到本地Maven仓库
echo "Installing $JAR_FILE to local Maven repository..."
mvn install:install-file -Dfile=$DOWNLOAD_DIR/out/artifacts/scana_sdk_java_jar/scana-sdk-java.jar -DgroupId=$GROUP_ID -DartifactId=$ARTIFACT_ID -Dversion=$VERSION -Dpackaging=jar

# 删除下载的tar.gz文件
echo "Cleaning up..."
rm -rf $DOWNLOAD_DIR

echo "Done."
