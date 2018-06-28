package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import po.User;
import service.IItemService;

@Controller
@SessionAttributes("user")
@RequestMapping("/file")
public class FileController {
    private static String accessKey = "YBOHrX9Lcvk73pqUqVIRNybKj4eE4mwBiuQ20VOs";
    private static String secretKey = "GLO5j0STcLseat2DNI0V1-gzlzLxkZZvJjMU4yAQ";
    private static String bucket = "photo";

    /**
     *
     * @Description: 上传图片到七牛云
     *
     * @param img 图片文件
     * @return 图片的链接地址
     *
     * @version: v1.0.0
     * @author: ZLK
     * @date: 2018/6/28 14:39
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/uploadImgToQn", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImgToQi(@RequestParam("img") MultipartFile img) {

        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        String key= UUID.randomUUID().toString();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        File file=new File("img");
        String name="";
        try {
            img.transferTo(file);
            Response response =   uploadManager.put(file,key,upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            name=putRet.key;
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "http://panmiwrml.bkt.clouddn.com/"+name;
    }


}
