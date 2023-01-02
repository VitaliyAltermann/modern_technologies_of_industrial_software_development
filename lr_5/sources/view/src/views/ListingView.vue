<template>
  <div class="listing">
    <h1>Вывод книг по ID автора</h1>

    <div>

        <!-- Ввод ИД автора с клавиатуры--> 
        <b-row>
          <b-col lq="2"> <label>ID автора</label></b-col>
          <b-col lq="4"> <b-form-input v-model="authorID"  @update="IDchange"
            type='number' 
            placeholder="Введите ID автора"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->
          <b-col lq="1"></b-col>
        </b-row>

        <!-- Выбор ИД автора из списка -->
        <b-row>
          <b-col lq="2"> <label>Выбирите ID автора из списка</label></b-col>
          <b-col lq="4"> <b-form-input v-model="authorID"  @update="IDchange"
            list="my-list-id">

          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->
          <b-col lq="1"></b-col>

          <datalist id="my-list-id">   <!-- создание списка для отображения -->
            <option v-for="(id, index) in ids" :key="index"> {{ id }} </option>
          </datalist>
        </b-row>


        <b-button :disabled.sync="bdis" variant="outline-primary" @click="sendRequest">Вывести книги автора с ID {{ authorID }} </b-button>
        <!-- :disabled.sync="переменная со значением" - доступна ли кнопка @click"имя метода" - вызов метода при нжатии  -->

        <div>
          <label> Результат запроса {{ error }}</label>
        </div>
    </div>


    <div>
      <label>{{ items }}</label>  <!-- Вывод ответа на запрос  -->
      <!-- постраничный переход для таблицы -->
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="books"
      ></b-pagination>

      <!-- Таблица для обображения результата с разбиением на страницы -->
      <b-table 
        id="books"
        :items="items"
        :per-page="perPage"
        :current-page="currentPage"
        striped hover 
        ></b-table>

    </div>


  </div>

</template>


<script>

import axios from 'axios'

export default{

  name:"ListingView",


  data(){
    
    return{
      authorID:'', //ИД автора для выборки книг
      bdis:false, //доступность кнопки
      items: [    //значения ответа
          { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
          { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
          { age: 89, first_name: 'Geneva', last_name: 'Wilson' },
          { age: 38, first_name: 'Jami', last_name: 'Carney' }
        ],

      perPage: 10,  //число строк на странице в таблице
      currentPage: 1, //текущая страница
      ids: ['1', '2', '3', '4', '5', '11', '111'],  //список ИД авторов
      error:''  //Ошибка при выполнении запроса или успешное выполнение
 

    }
  },

  computed: {   //вычисляемые значения
      rows() {  //количество строк в массиве
        return this.items.length
      }
  },


  mounted(){    //вызов методов при загрузке страницы
    this.sendRequestAuthorID()
  },

  methods:{

    sendRequest(){  // отправка запроса и отключение кнопки
      this.bdis=true
      if (this.authorID!=''){
        axios
          .get('/book-api/loadBookListByAuthorId',{
            params:{ authorId: this.authorID }
            }
          )
          .then(response => (this.items = response.data, this.error='Успешно'))
          .catch(e => {this.error=e})
      }
      else{alert("Необходимо заполнить поле ID автора!")}
    },

    IDchange(){     //включение кнопки при изменении ИД автора
      this.bdis=false
    },

    sendRequestAuthorID(){
      axios
          .get('/book-api/loadAuthorIdsList')
          .then(response => (this.ids = response.data, this.error='Успешно'))
          .catch(e => {this.error=e});
    }


  },



}

</script>

