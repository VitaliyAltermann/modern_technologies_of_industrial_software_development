<template>
  <div class="listingWaybill">
    <div>
        <h3 class="mx-auto" style="width: 100%">Просмотр накладных</h3>
         
        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
         <b-col lq="2"> <label>  </label></b-col>
        </b-row>

        <h4>Фильтры для отображения (пустое значение - фильтрация не производится)</h4>
        <!-- Подписи полей -->
        <b-row class="mx-auto">
          <b-col cols="2"> <label>Номер накладной</label></b-col>
          <b-col cols="2"> <label>Дата поступления (начало)</label></b-col>
          <b-col cols="2"> <label>Дата поступления (окончание)</label></b-col>
          <b-col cols="2"> <label>Стоимость (мин), руб</label></b-col>
          <b-col cols="2"> <label>Стоимость (макс), руб</label></b-col>
          <b-col cols="2"> <label>Сотрудник, принявший имущество</label></b-col>
        </b-row>


        <!-- Поля ввода -->
        <b-row class="mx-auto" style="width: 100%">

          <!-- Ввод номера -->
          <b-col cols="2" > <b-form-input v-model="number"  @update="infChange"
            type='text' 
            placeholder="Введите номер накладной для поиска"
            > 
                       
          </b-form-input></b-col>

          <!-- Ввод даты  -->
          <b-col cols="2"> <b-form-input v-model="dateMin"  @update="infChange"
            type='date' 
            >        
          </b-form-input></b-col>

          <!-- Ввод даты  -->
          <b-col cols="2"> <b-form-input v-model="dateMax"  @update="infChange"
            type='date' 
            >        
          </b-form-input></b-col>

          <!-- Ввод стоимости  -->
          <b-col cols="2"> <b-form-input v-model="costMin"  @update="infChange"
            type='number' 
            placeholder="Введите минимальную сумму в накладной для поиска"
            >        
          </b-form-input></b-col>

          <!-- Ввод стоимости  -->
          <b-col cols="2"> <b-form-input v-model="costMax"  @update="infChange"
            type='number' 
            placeholder="Введите максимальную сумму в накладной для поиска"
            >        
          </b-form-input></b-col>


          <!-- Выбор сотрудника для фильтрации -->
          <b-col cols="2">
            <b-form-select v-model="employeeId"  @change="infChange" style="width: 100%">
            <!-- выбор из поля результат в id options - откуда брать варианты далее поля значение и текст для отображения-->
            <template #first>
              <b-form-select-option :value=null>Не фильтровать</b-form-select-option>
              <b-form-select-option v-for="emp in employeers" :key="emp.id" :value="emp.id">{{ emp.name }}</b-form-select-option> <!-- формирование списка для выбора -->
            </template>
          </b-form-select>
          </b-col>

        </b-row>
        
        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row >

        <!-- кнопка отправки запроса данных -->
        <b-button :disabled.sync="bdis" variant="outline-primary"  @click="sendRequestAndChangeParam(null,null)">Вывести накладные в соответствии с фильтрами </b-button>
        <!-- :disabled.sync="переменная со значением" - доступна ли кнопка @click"имя метода" - вызов метода при нжатии  -->
    


        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
         <b-col lq="2"> <label>  </label></b-col>
        </b-row>


        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="2">
            <b-button :disabled.sync="btnLeftDis" variant="outline-primary"  @click="sendRequest(waybills[0].id,'left')"  class="mx-auto" style="width: 100%" >Предыдущая страница </b-button>
          </b-col>

          <b-col cols="2">
            <b-button :disabled.sync="btnRightDis" variant="outline-primary"  @click="sendRequest(waybills[limit-1].id,'right')"  class="mx-auto" style="width: 100%" >Следующая страница </b-button>
          </b-col>

          <b-col cols="4"></b-col>

          <b-col cols="2">
            <!-- кнопка редактирования накланой -->
            <b-button :disabled.sync="bupdatedis" variant="outline-primary"  @click="toUpdate()" class="mx-auto" style="width: 100%" v-if="accesCreateWaybill">Редактировать накладную </b-button>
            <!-- :disabled.sync="переменная со значением" - доступна ли кнопка @click"имя метода" - вызов метода при нжатии  -->
          </b-col>

          <b-col cols="2">
            <!-- кнопка создания накладной -->
            <b-button  variant="outline-primary"  @click="toCreate()" class="mx-auto" style="width: 100%" v-if="accesCreateWaybill">Создать накладную </b-button>
            <!-- :disabled.sync="переменная со значением" - доступна ли кнопка @click"имя метода" - вызов метода при нжатии  -->
          </b-col>
        </b-row>


        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
         <b-col lq="2"> <label>  </label></b-col>
        </b-row>
 
         

        <b-row class="mx-auto" style="width: 100%">
          
          <b-col cols="12">

            <!-- Таблица для отображения накладных -->
            <b-table 
              id="waybillstable"
              :items="waybills"
              :fields="fields"

              :select-mode="selectMode"
              selectable
              bordered
              @row-selected="onRowSelected"
              style="width: 100%"
            >

              <template #cell(detailsWaybill)="row"> <!-- Вставка таблицы в ячейки таблицы -->
                <b-table 
                  id="detail"
                  :items="row.item.detailsWaybill"    
                  :fields="fieldsDetail"
                  bordered        
                ></b-table>

              </template>


            </b-table>

          </b-col>
          
        </b-row>

        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
         <b-col lq="2"> <label>  </label></b-col>
        </b-row>

        <!-- кнопки перелистывания страниц -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="2">
            <b-button :disabled.sync="btnLeftDis" variant="outline-primary"  @click="sendRequest(waybills[0].id,'left')"  class="mx-auto" style="width: 100%" >Предыдущая страница </b-button>
          </b-col>

          <b-col cols="2">
            <b-button :disabled.sync="btnRightDis" variant="outline-primary"  @click="sendRequest(waybills[limit-1].id,'right')"  class="mx-auto" style="width: 100%" >Следующая страница </b-button>
          </b-col>
        </b-row>

        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row >
       
    </div>
  </div>
</template>


<script>

  


import axios from 'axios'
import Vue from 'vue'


export default{
name:'createWaybill', //имя для подключения



  data(){
    
    return{
      waybills:[  //существующие накладные
          { id: 1, contract: 'номер от числа', number: 'hfjds', dateOfReceipt: '11.12.2022', location:'base', cost:100, deliverer:'das', receiver:'Иванов Иван Иваноович', detailsWaybill:[    //детализация накладной
            { cargo:'имя ед.изм',number:7, price:5000,cost:35000},
            { cargo:'имя2 ед.изм2',number:8, price:8,cost:64}]
          },
          { id: 1, contract: '№ 51 от 31.12.2022', number: 'hfjds', dateOfReceipt: '11.12.2022', location:'base', cost:100, deliverer:'das', receiver:'Иванов Иван Иваноович', detailsWaybill:[    //детализация накладной
            { cargo:'имя ед.изм',number:7, price:5000,cost:35000},
            { cargo:'имя2 ед.изм2',number:8, price:8,cost:64}]
          },
          { id: 1, contract: '№ 51 от 31.12.2022', number: 'hfjds', dateOfReceipt: '11.12.2022', location:'base', cost:100, deliverer:'das', receiver:'Иванов Иван Иваноович', detailsWaybill:[    //детализация накладной
            { cargo:'имя ед.изм',number:7, price:5000,cost:35000},
            { cargo:'имя2 ед.изм2',number:8, price:8,cost:64}]
          }
      ],

      //token:localStorage.getItem("vue-token"),
      //token:Vue.$keycloak.tokenParsed(),

 
      fieldsDetail:[    //поля для отображения в таблице детализаций
        {key:'cargo', label:'Имущество, ед. изм.'},{key:'number', label:'Количество'},{key:'price', label:'Цена, руб'},{key:'cost', label:'Стоимость, руб'}
      ],        
      fields:[{key:'contract', label:'Договор'},{key:'number',label:'Номер накладной'},   
          {key:'dateOfReceipt', label:'Дата поступления имущества'},{key:'location', label:'Место размещения принятого имущества'},
          {key:'cost', label:'Общая стоимость поступившего имущества, руб'},{key:'deliverer', label:'Лицо, сдавшее имущество'},
          {key:'receiver', label:'Сотрудник, принявший имущество'},{key:'detailsWaybill', label:'Поступившее имущество'}],  //поля для отображения в таблице накладных
      

      bdis:false, //доступность кнопки отправки запроса по фильтрам
      btnLeftDis:true, //доступность кнопки предыдущей страницы
      btnRightDis:false, // доступность кнопки следующей страницы
      bupdatedis:true,
      selectMode:'single',  //тип выбора строк для таблицы

      
      error:'', //результат запроса

      // новые параметры фильтрации (изменяются всегда)
      employeeId:null, //выбранный для фильтрации сотрудник
      number:'',  //номер накладной
      dateMin:'',   //дата мин
      dateMax:'',   //дата макс
      costMin:'', //стоимость мин
      costMax:'', //стоимость макс

      // параметры фильтрации (изменяются по нажатию кнопки вывести накладные по фильтрам)
      femployeeId:null, //выбранный для фильтрации сотрудник
      fnumber:'',  //номер накладной
      fdateMin:null,   //дата мин
      fdateMax:'',   //дата макс
      fcostMin:'', //стоимость мин
      fcostMax:'', //стоимость макс

      indexForUpdete:null,  //индекс накладной в таблице, выбранной для редактирования
      accesCreateWaybill:Vue.$keycloak.hasResourceRole('editWaybill'),  //право доступа на страницу редактирования накладных

      
      //данные для заполнения полей фильтрации
      employeers:[
          { id: 1, name: 'a'},
          { id: 2, name: 'ab'},
          { id: 3, name: 'abc'},
          { id: 5, name: 'abcdfdsfdsdshgf'}],
      
     
      //данные для постраничного вывода
      limit:3,  //макс число строк за раз

    }
  },


  mounted(){    //вызов методов при загрузке страницы
    this.sendRequestEmployee()
    this.sendRequest(null,null)
    console.log(`${this.accesCreateWaybill}`)
  },

  

  methods:{
    

    sendRequest(anchor, direction){  // отправка запроса и отключение кнопки
      this.bdis=true
      
      

      axios
          .get('/server/waybill/loadWaybillFilter',{
            params:{
              anchor: anchor,
              direction:direction,
              limit:this.limit,

              number:this.fnumber,
              dateMin:this.fdateMin,
              dateMax:this.fdateMax,
              costMin:this.fcostMin,
              costMax:this.fcostMax,
              employeeId:this.femployeeId

            }  
          }
          )
          .then(response => (this.waybills = response.data.waybills, this.btnLeftDis=response.data.btnLeftDis,
                              this.btnRightDis=response.data.btnRightDis, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
      

        
    },


    sendRequestAndChangeParam(){

      this.femployeeId=this.employeeId
      this.fdateMax=this.dateMax
      this.fdateMin=this.dateMin
      this.fcostMin=this.costMin
      this.fcostMax=this.costMax
      this.fnumber=this.number

      this.sendRequest(null,null)

    },


    sendRequestEmployee(){  //получение списка всех сотрудников

      axios
          .get('/server/employee/loadEmployee')
          .then(response => (this.employeers = response.data, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});
    },



    infChange(){     //включение кнопки сохранения
      this.bdis=false
      
    },  

    onRowSelected(items){ //вызывается при выборе строки в таблице

      if (items.length>0){
        this.bupdatedis=false,       
       
       this.indexForUpdate=this.waybills.indexOf(items[0])
       
      }
      else{this.bupdatedis=true}
    },

    toUpdate(){ //переход на страницу создания накладной с выводом накладной в редактирование
      //console.log(`Обновление накладной ${this.indexForUpdate}`)
      this.$router.push({ path: `/createWaybill/${this.waybills[this.indexForUpdate].id}` }) //переход на страницу создания накладной, передача ид накладной для редактирования
    },

    toCreate(){ //переход на страницу накладной для создания
      this.$router.push({ path: `/createWaybill/0` }) //переход на страницу создания накладной 0 - создание
    }

  }

}
</script>

