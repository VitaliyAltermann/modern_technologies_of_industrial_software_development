<template>
  <div class="createWaybill">
    
    <div>
        <h3 class="mx-auto" style="width: 100%">{{pageName}}</h3>
        <!-- Подписи полей -->
        <b-row class="mx-auto" style="width: 100%">
         <!-- <b-col cols="4"> <label>Накладная (выберите, если необходимо изменить информацию)</label></b-col> -->
          <b-col cols="4"> <label>Договор</label></b-col>
          <b-col cols="4"> <label>Номер накладной</label></b-col>
          <b-col cols="4"> <label>Дата поступления имущества</label></b-col>
        </b-row>

        <!-- Поля ввода -->
        <b-row class="mx-auto" style="width: 100%">
          <!-- Выбор накладной для изменения сведений -->
         <!-- <b-col cols="4"> 
            <b-form-select v-model="waybill"  @change="selectChange">

            <template #first>
              <b-form-select-option :value=null>Добавить накладную</b-form-select-option>
              <b-form-select-option v-for="way in waybills" :key="way.id" :value="way">{{ way.number }} от {{way.dateOfReceipt}}</b-form-select-option> 
            </template>
          </b-form-select>
          </b-col> -->


          <!-- выбор договора -->
          <b-col cols="4">
            <b-form-select v-model="contractId"  @change="infChange" class="mx-auto" style="width: 100%">
            <!-- выбор из поля результат в id options - откуда брать варианты далее поля значение и текст для отображения-->
            <template #first>
              <b-form-select-option v-for="con in contracts" :key="con.id" :value="con.id">{{ con.number }} от {{con.date}}</b-form-select-option> <!-- формирование списка для выбора -->
            </template>
          </b-form-select>
          </b-col>

          <!-- номер накладной -->
          <b-col cols="4"> <b-form-input v-model="number"  @update="infChange"
            type='text' 
            placeholder="Введите номер накладной"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->

          <!-- дата поступления имущества -->
          <b-col cols="4"> <b-form-input v-model="dateOfReceipt"  @update="infChange"
            type='date' 
            placeholder="Введите дату поступления имущества"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->

        </b-row>

        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row >
        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row>
        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row>


        <!-- Подписи полей -->
        <b-row class="mx-auto" style="width: 100%">
          
          <b-col cols="4"> <label>Место размещения принятого имущества</label></b-col>
          <b-col cols="4"> <label>Лицо, сдавшее имущество (ФИО)</label></b-col>
          <b-col cols="4"> <label>Сотрудник, принявший имущество</label></b-col>
          
        </b-row>


        <!-- Поля ввода -->
        <b-row class="mx-auto" style="width: 100%">
        
          <!-- место размещения -->
          <b-col cols="4"> <b-form-input v-model="location"  @update="infChange"
            type='text' 
            placeholder="Введите место размещения принятого имущества"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->

          <!-- сдал -->
          <b-col cols="4"> <b-form-input v-model="deliverer"  @update="infChange"
            type='text' 
            placeholder="Введите ФИО лица, сдавшего имущества"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->

          <!-- принял -->
          <b-col cols="4">
            <b-form-select v-model="receiverId"  @change="infChange" class="mx-auto" style="width: 100%">
            <!-- выбор из поля результат в id options - откуда брать варианты далее поля значение и текст для отображения-->
            <template #first>
              <b-form-select-option v-for="emp in employeers" :key="emp.id" :value="emp.id">{{ emp.name }}</b-form-select-option> <!-- формирование списка для выбора -->
            </template>
          </b-form-select>
          </b-col>

        </b-row>

        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row >
        
    </div>

    <div>
      <label> Результат запроса {{ error }}</label>
      <label> Выбрано {{id}}</label>
    </div>

    <!-- пропуск строки -->
      <b-row class="mx-auto" style="width: 100%">
        <b-col cols="4"> <label>  </label></b-col>
      </b-row>

    
    <div>       
      <!-- Таблица для обображения детализации накладной -->
      <b-row class="mx-auto" style="width: 100%">
        <b-col cols="12">
          <b-table 
            id="detail"
            :items="detailsWaybill"
            :fields="fields"
            :select-mode="selectMode"
            selectable
            bordered
            @row-selected="onRowSelected"
        
        ></b-table>
        </b-col>
      </b-row>

      <b-button :disabled.sync="bdeletedis" variant="outline-primary" size="sm" @click="deleteRow" >Удалить выбранную строку </b-button>
        <!-- :disabled.sync="переменная со значением" - доступна ли кнопка @click"имя метода" - вызов метода при нжатии  -->

    </div>
     <!-- пропуск строки -->
      <b-row class="mx-auto" style="width: 100%">
        <b-col cols="4"> <label>  </label></b-col>
      </b-row>


    <!-- редактирование детализации -->

    <div>

        <!-- Подписи полей -->
        <b-row class="mx-auto" style="width: 100%">

          <b-col cols="4"> <label>Имущество</label></b-col>
          <b-col cols="4"> <label>Количество</label></b-col>
          <b-col cols="4"> <label>Цена, руб</label></b-col>

        </b-row>


        <!-- Поля ввода -->
        <b-row class="mx-auto" style="width: 100%">
          
          <!-- Имущество -->
          <b-col cols="4"> 
            <b-form-select v-model="detailWaybill.cargo"  @change="infDetailChange" class="mx-auto" style="width: 100%">
            <!-- выбор из поля результата в id options - откуда брать варианты далее поля значение и текст для отображения-->
              <b-form-select-option v-for="car in cargoes" :key="car.id" :value="car">{{ car.name }}, {{car.unit}}</b-form-select-option>
            </b-form-select>
          </b-col>

          <!-- Количесвто -->
          <b-col cols="4"> <b-form-input v-model="detailWaybill.number"  @update="infDetailChange"
            type='number' 
            placeholder="Введите количество имущества"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->

          <!-- Цена -->
          <b-col cols="4"> <b-form-input v-model="detailWaybill.price"  @update="infDetailChange"
            type='number' 
            placeholder="Введите цену имущества"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->

        </b-row>

        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row >

        <b-button :disabled.sync="bDetaildis" variant="outline-primary"  @click="addDetail">Добавить позицию в накладную </b-button>
        <!-- :disabled.sync="переменная со значением" - доступна ли кнопка @click"имя метода" - вызов метода при нжатии  -->
    

    </div>


    <div>
      <!-- пропуск строки -->
      <b-row class="mx-auto" style="width: 100%">
        <b-col cols="4"> <label>  </label></b-col>
      </b-row>
      <!-- пропуск строки -->
      <b-row class="mx-auto" style="width: 100%">
        <b-col cols="4"> <label>  </label></b-col>
      </b-row>



      <b-button :disabled.sync="bdis" variant="outline-primary" size="lg" @click="testNumberAndSendRequest">{{buttonText}}{{ number }} </b-button>
        <!-- :disabled.sync="переменная со значением" - доступна ли кнопка @click"имя метода" - вызов метода при нжатии  -->
    </div>


  </div>

</template>

<script>

  


import axios from 'axios'
//import Vue from 'vue'

export default{
name:'createWaybill', //имя для подключения

  data(){
    
    return{
      /*
      waybills:[  //существующие накладные
          { id: 1, contractId: 1, number: 'hfjds', dateOfReceipt: '2022-11-12', location:'base',  deliverer:'das', receiverId:1, detailsWaybill:[    //детализация накладной
            {id:2, cargoId:2,cargoName:'Принтер, шт.',number:7, price:5}]
          },
      ],
      */
      waybill:null, //выбранная накладная

      
      id:null,  //ИДнакладной (если не нул, то изменяются сведения)
      contractId:null,  //ИД договрора
      number:'',  //номер накладной
      dateOfReceipt:null,  //дата поступления
      location:'',  //место размещения
      deliverer:'', //лицо, доставившее груз (ФИО)
      receiverId:'',  //ИД принявшего груз


      selectMode:'single',  //тип выбора строк для детализаций
      fields:[{key:'cargoName', label:'Имущество'},{key:'number',label:'Количество'},{key:'price', label:'Цена, руб'}],  //поля для отображения в таблице детализации
      /*detailsWaybill:[    //детализация накладной
        {id:1, cargoId:1,number:5, price:3,cost:15,waybillId:1}
      ],*/
      detailsWaybill:[    //детализация накладной
        
      ],
      detailWaybill:{id:null, cargo:null, corgoName:'', number:null, price:null},  //заполняемая для добавления детализация
      indexForDelete:null,  //индекс строки для удаления
      
      bDetaildis:true,  //доступность кнопки добаления позиции в накладную
      bdis:false, //доступность кнопки
      bdeletedis:true, //доступность кнопки удаления строки
      buttonText:'Добавить накладную с номером: ',  //текст, отображаемый на кнопке
      pageName:'Создание накладной',  //тест заголовка вкладки
      error:'', //результат запроса

      //данные для заполнения полей накладной
      contracts:[ //договоры
          { id: 1, date: '22.11.2022', number: 'fdf',contractor: 'ffff'},
          { id: 2, date: 'a', number: 'fdfsfaf',contractor: 'ffff'},
          { id: 3, date: 'a', number: 'fdsafdsagfdasf',contractor: 'ffff'},
          ],

      cargoes:[ //имущество
          { id: 1, name: 'fdыыsgf', unit: 'шт.'},
          { id: 2, name: 'fdsаввнекнgf', unit: 'кг'},
          { id: 3, name: 'fdыыыыыsgf', unit: 'м'},
          ],

      

      employeers:[  //сотрудники
          { id: 1, name: 'a'},
          { id: 2, name: 'ab'},
          { id: 3, name: 'abc'},
          { id: 5, name: 'abcdfdsfdsdshgfhdjfgjf'}],


      //уникальность номера накладной
      numberUnique:false,

    }
  },


  mounted(){    //вызов методов при загрузке страницы

    console.log(`Параметр равен ${this.$route.params.id}`)

    if ((this.$route.params.id==undefined)||(this.$route.params.id==0)){  //если необходимо создавать накладную
      this.pageName='Создание накладной'
      this.id=null  //создание накладной
    }
    else{
      this.pageName='Изменение накладной'
      this.id=this.$route.params.id
      this.sendRequestWaybill(this.$route.params.id)
    }
    this.sendGetReguests()
  },

  

  methods:{

    sendRequest(){  // отправка запроса и отключение кнопки
      this.bdis=true

      if ((this.contractId != null)&&(this.dateOfReceipt != null)&&(this.dateOfReceipt != '')&&(this.number != '')&&(this.location != '')&&(this.deliverer != '')&&(this.receiverId != null)){
        if ((this.id !=null)&&(this.id!='')){ //если выбран ИД сотрудника, то обновляются данные, иначе - добавляются
          /*
          if (this.detailsWaybill.length != 0){
            this.detailsWaybill[0].cargoName='Привет, Виталя, это запрос на обновление накладной!'
          }
          */
          
          axios.put('/server/waybill/updateWaybill',{
            id:this.id,  //ИДнакладной (если не нул, то изменяются сведения)
            contractId:this.contractId,  //ИД договрора
            number:this.number,  //номер накладной
            dateOfReceipt:this.dateOfReceipt,  //дата поступления
            location:this.location,  //место размещения
            deliverer:this.deliverer, //лицо, доставившее груз (ФИО)
            receiverId:this.receiverId,  //ИД принявшего груз
            detailsWaybill:this.detailsWaybill
          })
          .then(response => (this.error=response.data+' успешно',this.waybill=null, this.selectChange()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
        }
        else{
          /*
          if (this.detailsWaybill != null){
            this.detailsWaybill[0].cargoName='Привет, Виталя, это запрос на добавление накладной!'
          }
          */
          if  (this.numberUnique==true){  //если номер уникален, то добаление
            axios.post('/server/waybill/createWaybill', {
              id:this.id,  //ИДнакладной (если не нул, то изменяются сведения)
              contractId:this.contractId,  //ИД договрора
              number:this.number,  //номер накладной
              dateOfReceipt:this.dateOfReceipt,  //дата поступления
              location:this.location,  //место размещения
              deliverer:this.deliverer, //лицо, доставившее груз (ФИО)
              receiverId:this.receiverId,  //ИД принявшего груз
              detailsWaybill:this.detailsWaybill
            })
            .then(response => (this.error=response.data+' успешно'))
            .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
          }
          else{
            alert("Накладная с введённым номером уже существует, проверьте корректность ввода")
          }
        }
      }
      else{alert("Необходимо заполнить все поля!")}
    },

    testNumberAndSendRequest(){ //проверка номера накладной на уникальность (если накладная создаётся)

      if (this.number != ''){ //если номер не пустой
        if ((this.id==null)||(this.id=='')) {  //если накладная создаётся, то проверяется уникальность номера
          axios
              .get('/server/waybill/isUniqueWaybillNumber', {params:{number:this.number}})
              .then(response => (this.numberUnique = response.data, this.error='Успешно',this.sendRequest()))
              .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});

        }
        else
        {
          this.sendRequest(); //если обновляется
        }
      }
      else{
        alert("Необходимо ввести номер накладной")
      }

    },


    sendRequestWaybill(id){    //запрос получения сведений о накладной
      axios
          .get('/server/waybill/readWaybill', {params:{id:id}})
          .then(response => (this.waybill = response.data, this.error='Успешно',this.selectChange()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});
    },

    sendGetReguests(){  //запросы получения сотрудников, иthis.мущества, договоров

      axios
          .get('/server/employee/loadEmployee')
          .then(response => (this.employeers = response.data, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});

      axios
          .get('/server/cargo/loadCargo')
          .then(response => (this.cargoes = response.data, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});

      axios
          .get('/server/contract/loadContract')
          .then(response => (this.contracts = response.data, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});
    },


    infChange(){     //включение кнопки сохранения
      this.bdis=false
      
    },

    selectChange(){     //включение кнопки при изменении ИД автора
      
      if ((this.waybill !=null)){ //если выбран ИД сотрудника, то обновляются данные, иначе - добавляются
        this.buttonText='Обновить информацию о накладной с номером: '
        this.id=this.waybill.id,  //ИДнакладной (если не нул, то изменяются сведения)
        this.contractId=this.waybill.contractId,  //ИД договрора
        this.number=this.waybill.number,  //номер накладной
        this.dateOfReceipt=this.waybill.dateOfReceipt,  //дата поступления
        this.location=this.waybill.location,  //место размещения
        this.deliverer=this.waybill.deliverer, //лицо, доставившее груз (ФИО)
        this.receiverId=this.waybill.receiverId,  //ИД принявшего груз        
        this.detailsWaybill=this.waybill.detailsWaybill

        this.infDetailChange()
      }
      else{
        this.pageName='Создание накладной'
        this.id=null

      }
    },

    onRowSelected(items){ //вызывается при выборе строки в таблице

      if (items.length>0){
        this.bdeletedis=false        
       
       this.indexForDelete=this.detailsWaybill.indexOf(items[0])
       
      }
      else{this.bdeletedis=true}
    },

    deleteRow(){  //удаление элемента из детализации
      this.bdeletedis=true;
      if (this.indexForDelete>-1){
        this.detailsWaybill.splice(this.indexForDelete,1)
        this.infChange()
      }
    },

    addDetail(){  //добавление детализации в накладную
      this.bDetaildis=true

      if ((this.detailWaybill.cargo != null)&&(this.detailWaybill.number != null)&&(this.detailWaybill.price != null)){
        this.detailWaybill.waybillId=this.id
        this.detailsWaybill.push({id:this.detailWaybill.id, cargoId:this.detailWaybill.cargo.id, cargoName:(this.detailWaybill.cargo.name+' '+this.detailWaybill.cargo.unit), number:this.detailWaybill.number, price:this.detailWaybill.price})
        this.infChange()

      }
      else{alert("Необходимо заполнить все поля!")}  
    },

    infDetailChange(){     //включение кнопки добаления детализации 
      this.bDetaildis=false
      
    },

  }

}
</script>
