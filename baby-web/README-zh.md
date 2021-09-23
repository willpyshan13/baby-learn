  # Install dependencies
  npm install
  
  # 可以通过如下操作解决npm速度慢的问题
  npm install --registry=https://registry.npm.taobao.org
  
  # serve with hot reload at localhost:9528
  npm run dev
  
  # build for production with minification
  npm run build
  npm run build:prod -- 正式环境
  npm run build:sit  -- 测试环境
  
  # build for production and view the bundle analyzer report
  npm run build --report