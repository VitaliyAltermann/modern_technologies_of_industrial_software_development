<template>
  <div class="createContract">
    <div>
        <h3>Добавление/изменение договора</h3>
        <!-- Подписи полей -->
        <b-row>
          
          <b-col lq="2"> <label>Договор (выберите, если необходимо изменить информацию)</label></b-col>
          <b-col lq="2"> <label>Дата договора</label></b-col>
          <b-col lq="2"> <label>Номер договора</label></b-col>
          <b-col lq="2"> <label>Контрагент</label></b-col>
          <b-col cols="1"></b-col>
          
          </b-row>

        <!-- Поля ввода -->
        <b-row>
          
          <!-- Выбор договора для изменения сведений -->
          <b-col lq="2"> 
            <b-form-select v-model="contract"  @change="selectChange">
            <!-- выбор из поля резнльтат в id options - откуда брать варианты далее поля значение и текст для отображения-->
            <template #first>
              <b-form-select-option :value=null>Добавить договор</b-form-select-option>
              <b-form-select-option v-for="con in contracts" :key="con.id" :value="con">{{ con.number }} от {{ con.date }}</b-form-select-option>
            </template>
          </b-form-select>
          </b-col>

          <!-- Ввод даты договора -->
          <b-col lq="2"> <b-form-input v-model="date"  @update="infChange"
            type='date' 
            placeholder="Введите дату договора"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->
          
          <!-- Ввод номера договора -->
          <b-col lq="2"> <b-form-input v-model="number"  @update="infChange"
            type='text' 
            placeholder="Введите номер договора"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->
          
          <!-- Ввод контрагента -->
          <b-col lq="2"> <b-form-input v-model="contractor"  @update="infChange"
            type='text' 
            placeholder="Введите контрагента"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->
          
          <b-col cols="1"></b-col>
          
        </b-row>

        <b-button :disabled.sync="bdis" variant="outline-primary" @click="sendRequest">{{buttonText}}{{ number }} </b-button>
        <!-- :disabled.sync="переменная со значением" - доступна ли кнопка @click"имя метода" - вызов метода при нжатии  -->
    </div>

    <div>
      <label> Результат запроса {{ error }}</label>
      <label> Выбрано {{id}}</label>
    </div>


  </div>
</template>


<script>

import axios from 'axios'


export default{
name:'createContract', //имя для подключения

  data(){
    
    return{
      contracts:[
          { id: 1, date: '2022-11-22', number: 'fdf',contractor: 'ffff'},
          { id: 2, date: 'a', number: 'fdfsfaf',contractor: 'ffff'},
          { id: 3, date: 'a', number: 'fdsafdsagfdasf',contractor: 'ffff'},
          ],
      contract:null,//выбранный договор

      number:'',  //номер договора
      id:null,  //ИДдоговора (если не нул, то изменяются сведения)
      date:null,  //дата договора
      contractor:'',  //контрагент

      bdis:false, //доступность кнопки
      buttonText:'Добавить договор с номером: ',  //текст, отображаемый на кнопке
      error:'' //результат запроса

      
    }
  },


  mounted(){    //вызов методов при загрузке страницы
    this.sendRequestContract()
  },
 
  

  methods:{

    sendRequest(){  // отправка запроса и отключение кнопки
      this.bdis=true

      if ((this.date != '')&&(this.date != null)&&(this.number != '')&&(this.contractor != '')){
        if ((this.id !=null)&&(this.id!='')){ //если выбран ИД сотрудника, то обновляются данные, иначе - добавляются
          axios.put('/server/contract/updateContract',{
            id:this.id,
            date:this.date,
            number:this.number,
            contractor:this.contractor
          })
          .then(response => (this.error=response.data+' успешно',this.sendRequestContract(),this.contract=null, this.selectChange()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
        }else{
          axios.post('/server/contract/createContract', {
            id:this.id,
            date:this.date,
            number:this.number,
            contractor:this.contractor
          })
          .then(response => (this.error=response.data+' успешно',this.sendRequestContract()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
        }        
      }
      else{alert("Необходимо заполнить все поля!")}
    },
  

    sendRequestContract(){    //запрос получения сведения о сотруднике
      axios
          .get('/server/contract/loadContract')
          .then(response => (this.contracts = response.data, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});
    },


    infChange(){     //включение кнопки при изменении ИД автора
      this.bdis=false      
    },

    selectChange(){     //включение кнопки при изменении ИД автора
      
      if ((this.contract !=null)){ //если выбран ИД сотрудника, то обновляются данные, иначе - добавляются
        this.buttonText='Обновить информацию о договоре с номером: '
        this.date=this.contract.date
        this.id=this.contract.id
        this.number=this.contract.number
        this.contractor=this.contract.contractor
      }
      else{
        this.buttonText='Добавить договор с номером: '
        this.id=null

      }
    }
  }

}
</script>
