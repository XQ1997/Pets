package com.fdy.util;

import com.qiniu.util.Auth;

/**图片上传下载工具类----使用七牛云
 * @author fdy
 * @date 2019年3月30日
 * @Version
 */
public class Qiniustore {
	
    private String accessKey = Config.get("qiniu.ak");
    private String secretKey = Config.get("qiniu.sk");
    private String bucket = Config.get("qiniu.bucket");

    public String getUploadToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }
	
}
