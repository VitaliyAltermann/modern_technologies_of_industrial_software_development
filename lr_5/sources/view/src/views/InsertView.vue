<template>
  <div class="insert">
    <div>

        <b-row>
          <b-col lq="2"> <label>Имя автора</label></b-col>
          <b-col lq="4"> <b-form-input v-model="name"  @update="nameChange"
            type='text' 
            placeholder="Введите имя автора"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->
          <b-col lq="1"></b-col>
        </b-row>

        <!-- Дата рождения автора -->
        <b-row>
          <b-col lq="2"> <label>Дата рождения</label></b-col>
          <b-col lq="4"> <b-form-input v-model="dateBirthday"  @update="nameChange"
            type='date' 
            placeholder="Введите дату рождения"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->
          <b-col lq="1"></b-col>
        </b-row>

        <b-button :disabled.sync="bdis" variant="outline-primary" @click="sendRequest">Добавить автора с именем {{ name }} </b-button>
        <!-- :disabled.sync="переменная со значением" - доступна ли кнопка @click"имя метода" - вызов метода при нжатии  -->
    </div>

    <div>
      <label> Результат запроса {{ error }}</label>
    </div>

  </div>
</template>

<script>

import axios from 'axios'


export default{
name:'InsertView', //имя для подключения

  data(){
    
    return{
      name:'',  //имя автора для добавления
      dateBirthday:'',//дата рождения автора
      bdis:false, //доступность кнопки
      error:'' //результат запроса

      
    }
  },

  methods:{

    sendRequest(){  // отправка запроса и отключение кнопки
      this.bdis=true
      if (this.name != ''){
        axios.post('/book-api/saveAuthor', {
            name:this.name,
            birthDate:this.dateBirthday
          })
          .then(this.error='успешно')
          .catch(e => {this.error=e})
      }
      else{alert("Необходимо заполнить поле Имя автора!")}
    },

    nameChange(){     //включение кнопки при изменении ИД автора
      this.bdis=false
    }
  }

}
</script>
