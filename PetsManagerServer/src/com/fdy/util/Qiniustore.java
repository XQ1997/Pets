package com.fdy.util;

import com.qiniu.util.Auth;

/**ͼƬ�ϴ����ع�����----ʹ����ţ��
 * @author fdy
 * @date 2019��3��30��
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
