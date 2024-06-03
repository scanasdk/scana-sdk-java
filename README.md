## Java SDK  <!-- {docsify-ignore} -->

### 安装SDK

sdk
暂未上传至中央仓库，需要下载 [scana-sdk-java](https://github.com/scanasdk/scana-sdk-java/releases/download/v3.0/scana-sdk.tar.gz)
引入到项目中使用

### 安装并引用jar包

1. 下载安装jar包

```shell
curl -sSL https://raw.githubusercontent.com/scanasdk/scana-sdk-java/main/setup_scana_sdk.sh | bash
```


2. pom.xml中引入
   ``````java
           <dependency>
               <groupId>com.newkms.qixincha</groupId>
               <artifactId>scana-sdk-java</artifactId>
               <version>3.0</version>
           </dependency>
   ``````

3. 刷新maven依赖即可使用

### 初始化客户端

向ScanA发起请求前需要创建一个client示例

```js
package
com.newkms.qixincha.scanasdkjavademo;

import com

.
newkms.qixincha.moderation.ModerationClient;

public

class ModerationClientTest {
    private static final
    String
    secretKey = "secretKey";
    private static final
    String
    appId = "appId";

    public static void

    main(String

    []
    args
) {
    // 实例化发起请求的client 对象
    ModerationClient
    client = new ModerationClient(appId, secretKey);
}
}
```

### v3相关接口说明

#### 灵活的接口配置

- 常用的http请求的各项配置，包括接口超时参数，http连接池连接数、空闲时间等核心参数。
- 切换请求协议，通过在请求对象中设置protocol字段，切换HTTP/HTTPS协议，默认HTTPS。
- 同步重试的各项配置，重试次数、重试间隔等。

http 各项参数配置

```js
package
com.newkms.qixincha.scanasdkjavademo.config;

import com

.
newkms.qixincha.client.ClientConfig;
import com

.
newkms.qixincha.http.HttpClientConfig;
import com

.
newkms.qixincha.http.ProtocolEnum;
import com

.
newkms.qixincha.moderation.ModerationClient;

public

class ConfigDemo {
    private static final
    String
    secretKey = "secretKey";
    private static final
    String
    appId = "appId";

    public static void

    main(String

    []
    args
) {
    // 实例化发起请求的client 对象
    ModerationClient
    client = new ModerationClient(appId, secretKey);
    // 获取sdk 客户端默认配置
    ClientConfig
    clientConfig = client.ClientConfig();
    // 设置sdk 失败重试次数
    clientConfig
.

    setMaxRetryCount(

    3
)
    ;
    // 设置域名地址
    clientConfig
.

    setModerationDomain(

    ""
)
    ;

    // 获取HTTP客户端默认配置
    HttpClientConfig
    httpClientConfig = clientConfig.getHttpClientConfig();
    // 设置超时时间
    httpClientConfig
.

    setSocketTimeoutMillis(

    6000
)
    ;
    // 设置请求协议
    httpClientConfig
.

    setProtocol(ProtocolEnum

.
    HTTPS
)
    ;
}
}

```

#### 文本同步检测

```js
package
com.newkms.qixincha.scanasdkjavademo.text;

import com

.
newkms.qixincha.moderation.ModerationClient;
import com

.
newkms.qixincha.moderation.image.v3.sync.ImageSyncModerationInput;
import com

.
newkms.qixincha.moderation.image.v3.sync.ImageSyncModerationOutput;
import com

.
newkms.qixincha.moderation.text.v3.sync.TextSyncModerationInput;
import com

.
newkms.qixincha.moderation.text.v3.sync.TextSyncModerationOutput;
import com

.
newkms.qixincha.request.BaseImage;
import com

.
newkms.qixincha.request.BaseText;
import com

.
newkms.qixincha.request.ImageTypeEnum;

import java

.
util.ArrayList;
import java

.
util.List;

/**
 * 文本同步检测demo
 */
public

class ModerationTextSyncClientDemo {

    private static final
    String
    secretKey = "secretKey";
    private static final
    String
    appId = "appId";
    private static final
    String
    businessId = "businessId";

    public static void

    main(String

    []
    args
) {
    // 实例化发起请求的client 对象
    ModerationClient
    client = new ModerationClient(appId, secretKey);
    // 实例化请求对象,传入业务ID
    TextSyncModerationInput
    textSyncModerationInput = new TextSyncModerationInput(businessId);
    // 设置检测内容
    textSyncModerationInput
.

    setText(createTexts

())
    ;
    // 响应结构体
    TextSyncModerationOutput
    resp = null;
    try {
    // 发起批量同步检测的请求
    resp = client.TextSyncModeration(textSyncModerationInput);
    System
.
    out
.

    printf(

    "响应码 :%d\n"
,
    resp
.

    getCode()

)
    ;
    System
.
    out
.

    printf(

    "响应信息 :%s\n"
,
    resp
.

    getMessage()

)
    ;
    System
.
    out
.

    printf(

    "检测类别 :%d\n"
,
    resp
.

    getModerationType()

)
    ;
    System
.
    out
.

    printf(

    "检测结果 :%s"
,
    resp
.

    getModerationResult()

)
    ;

}

catch
(Exception
e
)
{
    e.printStackTrace();
}
}

/**
 * 创建批量文本数据
 */
/**
 * 创建批量文本数据
 */
private
static
List < BaseText > createTexts()
{
    int
    contentCount = 1;
    List < BaseText > textObj = new ArrayList < > (contentCount);
    BaseText
    text = null;
    for (int i = 0;
    i < contentCount;
    i++
)
    {
        text = new BaseText();
        text.setContentId("数据唯一标识");
        text.setData("待检测内容");
        // 请求对象中的其他参数如果有需要，请参考官方接口文档中字段说明，按需添加
        textObj.add(text);
    }
    return textObj;
}

}
```

响应结果查看[v3 API](ecmc-docs/v3/api/text-sync.md)

#### 文本异步检测

```js
package
com.newkms.qixincha.scanasdkjavademo.text;

import com

.
newkms.qixincha.moderation.ModerationClient;
import com

.
newkms.qixincha.moderation.text.v3.async.TextAsyncModerationInput;
import com

.
newkms.qixincha.moderation.text.v3.async.TextAsyncModerationOutput;
import com

.
newkms.qixincha.request.BaseText;

import java

.
util.ArrayList;
import java

.
util.List;

/**
 * 文本异步检测demo
 */
public

class ModerationTextAsyncClientDemo {
    private static final
    String
    secretKey = "secretKey";
    private static final
    String
    appId = "appId";
    private static final
    String
    businessId = "businessId";

    public static void

    main(String

    []
    args
) {
    // 实例化发起请求的client 对象
    ModerationClient
    client = new ModerationClient(appId, secretKey);
    // 实例化请求对象,传入业务ID
    TextAsyncModerationInput
    textAsyncModerationInput = new TextAsyncModerationInput(businessId);
    // 设置检测内容
    textAsyncModerationInput
.

    setText(createTexts

())
    ;
    // 检测结果
    TextAsyncModerationOutput
    resp = null;
    try {
    // 发起批量同步检测的请求
    resp = client.TextAsyncModeration(textAsyncModerationInput);
    System
.
    out
.

    printf(

    "响应码 :%s\n"
,
    resp
.

    getCode()

)
    ;
    System
.
    out
.

    printf(

    "响应信息 :%s\n"
,
    resp
.

    getMessage()

)
    ;
    System
.
    out
.

    printf(

    "检测类别 :%s"
,
    resp
.

    getModerationType()

)
    ;
}

catch
(Exception
e
)
{
    e.printStackTrace();
}
}

/**
 * 创建批量文本数据
 */
/**
 * 创建批量文本数据
 */
private
static
List < BaseText > createTexts()
{
    int
    contentCount = 1;
    List < BaseText > textObj = new ArrayList < > (contentCount);
    BaseText
    text = null;
    for (int i = 0;
    i < contentCount;
    i++
)
    {
        text = new BaseText();
        text.setContentId("数据唯一标识");
        text.setData("待检测的文本");
        // 请求对象中的其他参数如果有需要，请参考官方接口文档中字段说明，按需添加
        textObj.add(text);
    }
    return textObj;
}
}
```

响应结果查看[v3 API](ecmc-docs/v3/api/text.md)

#### 图片同步检测

```java
package com.newkms.qixincha.scanasdkjavademo.image;

import com.newkms.qixincha.moderation.ModerationClient;
import com.newkms.qixincha.moderation.image.v3.sync.ImageSyncModerationInput;
import com.newkms.qixincha.moderation.image.v3.sync.ImageSyncModerationOutput;
import com.newkms.qixincha.request.BaseImage;
import com.newkms.qixincha.request.ImageTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片同步检测demo
 */
public class ModerationImageSyncClientTest {
    private static final String secretKey = "secretKey";
    private static final String appId = "appId";
    private static final String businessId = "businessId";

    public static void main(String[] args) {
        // 实例化发起请求的client 对象
        ModerationClient imageClient = new ModerationClient(appId, secretKey);
        // 实例化请求对象,传入业务ID
        ImageSyncModerationInput imageSyncModerationInput = new ImageSyncModerationInput(businessId);
        // 设置检测内容
        imageSyncModerationInput.setImages(createImages());
        // 响应结构体
        ImageSyncModerationOutput resp = null;
        try {
            // 发起批量同步检测的请求
            resp = imageClient.ImageSyncModeration(imageSyncModerationInput);
            System.out.printf("响应码 :%d\n", resp.getCode());
            System.out.printf("响应信息 :%s\n", resp.getMessage());
            System.out.printf("图片检测类别 :%d\n", resp.getModerationType());
            System.out.printf("图片检测结果 :%s", resp.getModerationResult());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建批量图片数据
     */
    private static List<BaseImage> createImages() {
        int contentCount = 1;
        List<BaseImage> imageObj = new ArrayList<>(contentCount);
        BaseImage image = null;
        for (int i = 0; i < contentCount; i++) {
            image = new BaseImage();
            image.setContentId("测试sdk");
            image.setData("https://images-rs.zsxq.com/Fo2bIKw8e8JkASNNnjPnnlD1psNU?e=1712303090&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:gV6Ox7ive-7pAWw9TN798ltryv4=");
            // 图片类型  URL or BASE64
            image.setType(ImageTypeEnum.URL.toInteger());
            // 请求对象中的其他参数如果有需要，请参考官方接口文档中字段说明，按需添加
            imageObj.add(image);
        }
        return imageObj;
    }
}
```

响应结果查看[v3 API](ecmc-docs/v3/api/image-sync.md)

#### 图片异步检测

```js
package
com.newkms.qixincha.scanasdkjavademo.image;

import com

.
newkms.qixincha.moderation.ModerationClient;
import com

.
newkms.qixincha.moderation.image.v3.async.ImageAsyncModerationInput;
import com

.
newkms.qixincha.moderation.image.v3.async.ImageAsyncModerationOutput;
import com

.
newkms.qixincha.request.BaseImage;
import com

.
newkms.qixincha.request.ImageTypeEnum;

import java

.
util.ArrayList;
import java

.
util.List;

/**
 * 图像异步检测demo
 */
public

class ModerationImageAsyncClientTest {
    private static final
    String
    secretKey = "secretKey";
    private static final
    String
    appId = "appId";
    private static final
    String
    businessId = "businessId";

    public static void

    main(String

    []
    args
) {
    // 实例化发起请求的client 对象
    ModerationClient
    client = new ModerationClient(appId, secretKey);
    // 实例化请求对象,传入业务ID
    ImageAsyncModerationInput
    imageAsyncModerationInput = new ImageAsyncModerationInput(businessId);
    // 设置检测内容
    imageAsyncModerationInput
.

    setImages(createImages

())
    ;
    ImageAsyncModerationOutput
    resp = null;
    try {
    // 发起批量同步检测的请求
    resp = client.ImageAsyncModeration(imageAsyncModerationInput);
    System
.
    out
.

    printf(

    "响应码 :%d\n"
,
    resp
.

    getCode()

)
    ;
    System
.
    out
.

    printf(

    "响应信息 :%s\n"
,
    resp
.

    getMessage()

)
    ;
    System
.
    out
.

    printf(

    "图片检测类别 :%d"
,
    resp
.

    getModerationType()

)
    ;
}

catch
(Exception
e
)
{
    e.printStackTrace();
}
}

/**
 * 创建批量图片数据
 */
private
static
List < BaseImage > createImages()
{
    int
    contentCount = 1;
    List < BaseImage > imageObj = new ArrayList < > (contentCount);
    BaseImage
    image = null;
    for (int i = 0;
    i < contentCount;
    i++
)
    {
        image = new BaseImage();
        image.setContentId("测试sdk");
        image.setData("https://images-rs.zsxq.com/Fo2bIKw8e8JkASNNnjPnnlD1psNU?e=1712303090&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:gV6Ox7ive-7pAWw9TN798ltryv4=");
        image.setType(ImageTypeEnum.URL.toInteger());
        // 请求对象中的其他参数如果有需要，请参考官方接口文档中字段说明，按需添加
        imageObj.add(image);
    }
    return imageObj;
}

}
```

响应结果查看[v3 API](ecmc-docs/v3/api/image.md)

#### 音频异步检测

```js
package
com.newkms.qixincha.scanasdkjavademo.audio;

import com

.
newkms.qixincha.moderation.ModerationClient;
import com

.
newkms.qixincha.moderation.audio.v3.AudioModerationInput;
import com

.
newkms.qixincha.moderation.audio.v3.AudioModerationOutput;

/**
 * 音频异步检测demo
 */
public

class ModerationAudioAsyncClientTest {
    private static final
    String
    secretKey = "secretKey";
    private static final
    String
    appId = "appId";
    private static final
    String
    businessId = "businessId";

    public static void

    main(String

    []
    args
) {
    // 实例化发起请求的client 对象
    ModerationClient
    client = new ModerationClient(appId, secretKey);
    // 实例化请求对象,传入业务ID
    AudioModerationInput
    audioModerationInput = new AudioModerationInput(businessId);
    // 设置检测额外信息
    audioModerationInput
.

    setExtra(

    "测试"
)
    ;
    // 检测音频地址
    audioModerationInput
.

    setUrl(

    "https://files-rs.zsxq.com/lpAtQmTCJ8YKCVXFueVSncMSOL8C?attname=%E9%A3%8E%E7%94%B5%26%E7%94%B5%E7%BD%91%E5%91%A8%E5%BA%A6%E6%95%B0%E6%8D%AE%E4%B8%8E%E8%A7%82%E7%82%B9%E6%B1%87%E6%8A%A5+231007.mp3&e=1712308798&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:CVP8s58Shu7CbIZmom8l_JFOlG4="
)
    ;
    // 检测内容ID
    audioModerationInput
.

    setContentId(

    "test-audio"
)
    ;
    // 检测请求结果
    AudioModerationOutput
    resp = null;
    try {
    resp = client.AudioAsyncModeration(audioModerationInput);
    System
.
    out
.

    printf(

    "响应码 :%s\n"
,
    resp
.

    getCode()

)
    ;
    System
.
    out
.

    printf(

    "响应信息 :%s\n"
,
    resp
.

    getMessage()

)
    ;
    System
.
    out
.

    printf(

    "检测类别 :%s"
,
    resp
.

    getModerationType()

)
    ;
}

catch
(Exception
e
)
{
    e.printStackTrace();
}
}
}
```

响应结果查看[v3 API](ecmc-docs/v3/api/audio.md)

#### 视频异步检测

```js
package
com.newkms.qixincha.scanasdkjavademo.video;

import com

.
newkms.qixincha.moderation.ModerationClient;
import com

.
newkms.qixincha.moderation.video.v3.VideoModerationInput;
import com

.
newkms.qixincha.moderation.video.v3.VideoModerationOutput;

/**
 * 视频异步检测demo
 */
public

class ModerationVideoAsyncClientTest {
    private static final
    String
    secretKey = "secretKey";
    private static final
    String
    appId = "appId";
    private static final
    String
    businessId = "businessId";

    public static void

    main(String

    []
    args
) {
    // 实例化发起请求的client 对象
    ModerationClient
    client = new ModerationClient(appId, secretKey);
    // 实例化请求对象,传入业务ID
    VideoModerationInput
    videoModerationInput = new VideoModerationInput(businessId);
    // 设置检测额外信息
    videoModerationInput
.

    setExtra(

    "测试"
)
    ;
    // 检测视频地址
    videoModerationInput
.

    setUrl(

    "https://rs-videos.zsxq.com/c3w3vfhmsiw0uofgs11kxm7wdnnf1csg.m3u8?MtsHlsUriToken=ZW5jcnlwdGVkPTAmdWlkPTAmdmlkPTg1Njg1JmV0PTE3MDE5MzY0MDI3MTkmc2lnbj0wRTg5NjhEMjQ2QURBRDBFMUEzOUJGMjU0OEMyM0YwQQ%3D%3D"
)
    ;

    // 检测内容ID
    videoModerationInput
.

    setContentId(

    "test-video"
)
    ;
    VideoModerationOutput
    resp = null;
    try {
    resp = client.VideoAsyncModeration(videoModerationInput);
    System
.
    out
.

    printf(

    "响应码 :%s\n"
,
    resp
.

    getCode()

)
    ;
    System
.
    out
.

    printf(

    "响应信息 :%s\n"
,
    resp
.

    getMessage()

)
    ;
    System
.
    out
.

    printf(

    "检测类别 :%s"
,
    resp
.

    getModerationType()

)
    ;
}

catch
(Exception
e
)
{
    e.printStackTrace();
}
}
}
```

响应结果查看[v3 API](ecmc-docs/v3/api/video.md)

#### 文档异步检测

```js
package
com.newkms.qixincha.scanasdkjavademo.doc;

import com

.
newkms.qixincha.moderation.ModerationClient;
import com

.
newkms.qixincha.moderation.doc.v3.DocModerationInput;
import com

.
newkms.qixincha.moderation.doc.v3.DocModerationOutput;

/**
 * 文档异步检测demo
 */
public

class ModerationDocAsyncClientTest {
    private static final
    String
    secretKey = "secretKey";
    private static final
    String
    appId = "appId";
    private static final
    String
    businessId = "businessId";

    public static void

    main(String

    []
    args
) {
    // 实例化发起请求的client 对象
    ModerationClient
    client = new ModerationClient(appId, secretKey);
    // 实例化请求对象,传入业务ID
    DocModerationInput
    docModerationInput = new DocModerationInput(businessId);
    // 设置检测额外信息
    docModerationInput
.

    setExtra(

    "测试"
)
    ;
    // 检测文档地址
    docModerationInput
.

    setUrl(

    "https://files-rs.zsxq.com/lpAtQmTCJ8YKCVXFueVSncMSOL8C?attname=%E9%A3%8E%E7%94%B5%26%E7%94%B5%E7%BD%91%E5%91%A8%E5%BA%A6%E6%95%B0%E6%8D%AE%E4%B8%8E%E8%A7%82%E7%82%B9%E6%B1%87%E6%8A%A5+231007.mp3&e=1712308798&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:CVP8s58Shu7CbIZmom8l_JFOlG4="
)
    ;
    // 检测内容ID
    docModerationInput
.

    setContentId(

    "test-audio"
)
    ;
    DocModerationOutput
    resp = null;
    try {
    // 发起批量同步检测的请求
    resp = client.DocAsyncModeration(docModerationInput);
    System
.
    out
.

    printf(

    "响应码 :%s\n"
,
    resp
.

    getCode()

)
    ;
    System
.
    out
.

    printf(

    "响应信息 :%s\n"
,
    resp
.

    getMessage()

)
    ;
    System
.
    out
.

    printf(

    "检测类别 :%s"
,
    resp
.

    getModerationType()

)
    ;
}

catch
(Exception
e
)
{
    e.printStackTrace();
}
}
}
```

响应结果查看[v3 API](ecmc-docs/v3/api/doc.md)

```

