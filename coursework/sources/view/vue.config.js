const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  runtimeCompiler: true, //компиляция во время выполнения

  devServer: {
    port: 3000,     //порт запуска
    proxy: {    //настройка проксирования
        '/server': {  //все запросы на адрес
            target: 'http://backend:8081/coursework/api/v1',  // будут направлены сюда
            changeOrigin: true,  
            pathRewrite: {
                '^/server': ''    //при этом эта часть пути будет удалена
            }
        }
    }
  }
})
