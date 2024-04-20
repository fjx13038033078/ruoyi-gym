<template>
  <div>
  <div id="curtain">
    <h1 data-heading="乐">健身俱乐部管理系统</h1>
  </div>
  <div>
    <el-row style="margin-top: 20px;">
      <el-col :span="12">
        <el-card style="margin-right: 20px; height: 420px;">
          <h3 slot="header">站外导航</h3>
          <el-carousel :interval="4000" height="300px">
            <el-carousel-item>
              <a href="https://www.baidu.com" target="_blank">
                <img src="../assets/images/01.jpg" alt="Image 1" style="width: 100%;">
              </a>
            </el-carousel-item>
            <el-carousel-item>
              <a href="https://www.jd.com" target="_blank">
                <img src="../assets/images/02.jpg" alt="Image 2" style="width: 100%;">
              </a>
            </el-carousel-item>
            <el-carousel-item>
              <a href="https://www.taobao.com" target="_blank">
                <img src="../assets/images/03.jpg" alt="Image 3" style="width: 100%;">
              </a>
            </el-carousel-item>
          </el-carousel>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card style="margin-right: 20px; height: 420px;">
          <h3 slot="header">系统简介</h3>
          <p>请在这里填写更多的文字说明</p>
        </el-card>
      </el-col>
    </el-row>
  </div>
  </div>
</template>
<script>
</script>

<style scoped lang="scss">
$h1: rgba(45, 45, 45, 1);
$blue: #98b5cc;
$yellow: #e30f16;
$outline: rgba(#fff, .4);
$shadow: rgba($yellow, .5);

#curtain {
  background: linear-gradient(45deg, rgb(182, 182, 182) 9%, rgb(56, 56, 56) 100%);
  width: 100%;
  height: 200px;
  border-radius: 30px;
}

h1 {
  font-family: '阿里妈妈东方大楷 Regular', sans-serif;
  font-size: 80px;
  text-align: center;
  line-height: 1;
  margin: 0;
  top: 13%;
  left: 50%;
  transform: translate(-50%, -50%);
  position: absolute;
  color: $h1;
  letter-spacing: 1rem;

  &:before {
    content: attr(data-heading);
    position: absolute;
    overflow: hidden;
    color: $yellow;
    width: 100%;
    z-index: 5;
    text-shadow: none;
    left: 325px;
    text-align: left;
    animation: flicker 3s linear infinite;
  }
}

@keyframes flicker {

  0%,
  19.999%,
  22%,
  62.999%,
  64%,
  64.999%,
  70%,
  100% {
    opacity: .99;
    text-shadow: -1px -1px 0 $outline, 1px -1px 0 $outline,
    -1px 1px 0 $outline, 1px 1px 0 $outline,
    0 -2px 8px, 0 0 2px, 0 0 5px #ff7e00,
    0 0 5px #ff4444, 0 0 2px #ff7e00, 0 2px 3px #000;
  }

  20%,
  21.999%,
  63%,
  63.999%,
  65%,
  69.999% {
    opacity: 0.4;
    text-shadow: none;
  }
}

@font-face {
  font-family: "阿里妈妈东方大楷 Regular";
  font-weight: 400;
  src: url("../assets/fonts/AlimamaDongFangDaKai-Regular.woff2") format("woff2"),
  url("../assets/fonts/AlimamaDongFangDaKai-Regular.woff") format("woff");
  font-display: swap;
}

</style>
