const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  runtimeCompiler: true, //компиляция во время выполнения

  devServer: {
    port: 3000,     //порт запуска
    proxy: {    //настройка проксирования
        '/book-api': {  //все запросы на адрес
            target: 'http://127.0.0.1:8080/books/api/v1',  // будут направлены сюда
            changeOrigin: true,
            pathRewrite: {
                '^/book-api': ''    //при этом эта часть пути будет удалена
            }
        }
    }
  }
})
