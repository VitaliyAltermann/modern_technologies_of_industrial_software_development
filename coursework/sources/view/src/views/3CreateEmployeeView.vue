<template>
  <div class="createEmployee">
    <div>
        <h3 class="mx-auto" style="width: 100%">Добавление сотрудника / изменение информации о сотруднике</h3>
        <!-- Подписи полей -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="2"> </b-col>
          <b-col cols="4"> <label>Cотрудник (выберите, если необходимо изменить информацию)</label></b-col>
          <b-col cols="4"> <label>ФИО сотрудника</label></b-col>
          <b-col cols="2"> </b-col>
          </b-row>

        <!-- Поля ввода -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="2"> </b-col>
          <!-- Выбор сотрудника для изменения сведений -->
          <b-col cols="4"> 
            <b-form-select v-model="employee"  @change="selectChange" class="mx-auto" style="width: 100%">
            <!-- выбор из поля резнльтат в id options - откуда брать варианты далее поля значение и текст для отображения-->
            <template #first>
              <b-form-select-option :value=null>Добавить сотрудника</b-form-select-option>
              <b-form-select-option v-for="emp in employeers" :key="emp.id" :value="emp">{{ emp.name }}</b-form-select-option> <!-- формирование списка для выбора -->
            </template>
          </b-form-select>
          </b-col>

          <!-- Ввод ФИО -->
          <b-col cols="4"> <b-form-input v-model="name"  @update="infChange"
            type='text' 
            placeholder="Введите ФИО сотрудника"> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->
          <b-col cols="2"> </b-col>
        </b-row>

        <!-- пропуск строки -->
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row >

        <b-button :disabled.sync="bdis" variant="outline-primary" @click="sendRequest">{{buttonText}}{{ name }} </b-button>
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
name:'createEmployee', //имя для подключения

  data(){
    
    return{
      employeers:[
          { id: 1, name: 'a'},
          { id: 2, name: 'ab'},
          { id: 3, name: 'abc'},
          { id: 5, name: 'abcdfdsfdsdshgdfhjg,lkhgfjghsdgfhdjfgjf'}],
      employee:null, //выбранный сотрудник

      name:'',  //ФИО сотрудника
      id:null,  //ИДсотрудника (если не нул, то изменяются сведения)

      bdis:false, //доступность кнопки
      buttonText:'Добавить сотруника с ФИО: ',  //текст, отображаемый на кнопке
      error:'' //результат запроса

      
    }
  },


  mounted(){    //вызов методов при загрузке страницы
    this.sendRequestEmployee()
  },

  

  methods:{

    sendRequest(){  // отправка запроса и отключение кнопки
      this.bdis=true

      if (this.name != ''){
        if ((this.id !=null)&&(this.id!='')){ //если выбран ИД сотрудника, то обновляются данные, иначе - добавляются
          axios.put('/server/employee/updateEmployee',{
            id:this.id,
            name:this.name,
            /*
            headers: {
                    'Authorization': 'Bearer ' + this.$props.keycloak.token
                }
            */
          })
          .then(response => (this.error=response.data+' успешно', this.sendRequestEmployee(),this.employee=null, this.selectChange()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
        }else{
          axios.post('/server/employee/createEmployee', {

            id:this.id,
            name:this.name
          })
          .then(response => (this.error=response.data+' успешно', this.sendRequestEmployee()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
        }        
      }
      else{alert("Необходимо заполнить поле ФИО сотрудника!")}
    },


    sendRequestEmployee(){    //запрос получения сведения о сотруднике
      axios
          .get('/server/employee/loadEmployee')
          .then(response => (this.employeers = response.data, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});
    },


    infChange(){     //включение кнопки при изменении ИД автора
      this.bdis=false
      
    },

    selectChange(){     //включение кнопки при изменении ИД автора
      
      if ((this.employee !=null)){ //если выбран ИД сотрудника, то обновляются данные, иначе - добавляются
        this.buttonText='Обновить информацию о сотруднике с ФИО: '
        this.name=this.employee.name
        this.id=this.employee.id
      }
      else{
        this.buttonText='Добавить сотруника с ФИО: '
        this.id=null

      }
    }
  }

}
</script>
