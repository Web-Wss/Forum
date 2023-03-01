import axios from "axios";
import { Toast } from "vant";
import store from "../store";

function upLoadImgPublish(file, bar_id, titlevalue, contentvalue) {
  // const uploadFile = [];
  // for (let i = 0; i < file.length; i++) {
  //   uploadFile[i] = file[i];
  //   // console.log(file[i]);
  // }
  // console.log(bar_id);
  // console.log(titlevalue);
  // console.log(contentvalue);
  // file为 你读取成功的回调文件信息
  let params = new FormData();
  if (file instanceof Array && file.length) {
    console.log(file);
    file.forEach((item, i) => {
      params.append("uploadFile", item.file);
    });
  }
  // params.append("uploadFile", uploadFile);
  // params.append("token", store.getters.getToken);
  params.append("user_id", localStorage.getItem("user_id"));
  params.append("bar_id", bar_id);
  params.append("titlevalue", titlevalue);
  params.append("contentvalue", contentvalue);
  let config = {
    headers: {
      // 添加请求头
      "Content-Type": "multipart/form-data",
    },
  };
  return new Promise((resolve, reject) => {
    axios
      .post("/api/posts/publish", params, config)
      .then((res) => {
        console.log(res);
        if (res.status == 200) {
          resolve(res.data);
        } else {
          reject(res.data.data);
        }
      })
      .catch((err) => {
        Toast.file("系统异常");
        reject(err);
      });
  });
}

export default upLoadImgPublish;
