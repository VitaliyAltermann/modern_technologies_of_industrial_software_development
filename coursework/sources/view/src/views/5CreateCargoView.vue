<template>
  <div class="createCargo">
    <div>
        <h3 >Добавление/изменение имущества</h3>
        <!-- Подписи полей -->
        <b-row class="mx-auto" style="width: 100%">
          
          <b-col cols="4"> <label>Имущество (выберите, если необходимо изменить информацию)</label></b-col>
          <b-col cols="4"> <label>Наименование</label></b-col>
          <b-col cols="4"> <label>Единицы измерения</label></b-col>
          
          </b-row>

        <!-- Поля ввода -->
        <b-row class="mx-auto" style="width: 100%">
          
          <!-- Выбор имущества для изменения сведений -->
          <b-col cols="4"> 
            <b-form-select v-model="cargo"  @change="selectChange" id="selector" class="mx-auto" style="width: 100%"> 
            <!-- выбор из поля результата в id options - откуда брать варианты далее поля значение и текст для отображения-->
            <template #first>
              <b-form-select-option :value=null>Добавить имущество</b-form-select-option>
              <b-form-select-option v-for="car in cargoes" :key="car.id" :value="car">{{ car.name }}, {{car.unit}}</b-form-select-option>
            </template>
          </b-form-select>
          </b-col>

          <!-- Ввод наименования имущества -->
          <b-col cols="4"> <b-form-input v-model="name"  @update="infChange"
            type='text' 
            placeholder="Введите наименование имущества" required=true> 
            required            
          </b-form-input></b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->

          <!-- Ввод единицы измерения -->
          <b-col cols="4"> <b-form-select v-model="unit"  @change="infChange" style="width: 100%">
            <!-- выбор из поля резнльтат в id options - откуда брать варианты далее поля значение и текст для отображения-->
                          
              <b-form-select-option v-for="un in units" :key="un" :value="un">{{ un }}</b-form-select-option>
            </b-form-select>
          </b-col> <!-- v-model - переменная, в которую сохранить значение, type - тип вводимого значения, required - обязательно для заполнения-->

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
name:'createCargo', //имя для подключения

  data(){
    
    return{
      cargoes:[
          { id: 1, name: 'fdыыsgf', unit: 'шт.'},
          { id: 2, name: 'fdsаввнекнgf', unit: 'кг'},
          { id: 3, name: 'fdыыыыыsgf', unit: 'м'},
          ],
      cargo:null, //выбранное имущество

      name:'',  //наименование имущества
      id:null,  //ИДимущества (если не нул, то изменяются сведения)
      unit:null,  //единица измерения
      
      bdis:false, //доступность кнопки
      buttonText:'Добавить имущество с наименованием: ',  //текст, отображаемый на кнопке
      error:'', //результат запроса

      
      units:['шт.', 'кг','т','м','л','пар','Другое']

    }
  },


  mounted(){    //вызов методов при загрузке страницы
    this.sendRequestCargo()
  },

  

  methods:{

    sendRequest(){  // отправка запроса и отключение кнопки
      this.bdis=true

      if ((this.name != '')&&(this.unit != null)){
        if ((this.id !=null)&&(this.id!='')){ //если выбран ИД сотрудника, то обновляются данные, иначе - добавляются
          axios.put('/server/cargo/updateCargo',{
            id:this.id,
            name:this.name,
            unit:this.unit,
            
          })
          .then(response => (this.error=response.data+' успешно',this.sendRequestCargo(), this.cargo=null, this.selectChange()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
        }else{
          axios.post('/server/cargo/createCargo', {
            id:this.id,
            name:this.name,
            unit:this.unit, 
          })
          .then(response => (this.error=response.data+' успешно',this.sendRequestCargo()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
        }        
      }
      else{alert("Необходимо заполнить все поля!")}
    },


    sendRequestCargo(){    //запрос получения сведения об имуществе
      axios
          .get('/server/cargo/loadCargo')
          .then(response => (this.cargoes = response.data, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});
    },


    infChange(){     //включение кнопки при изменении ИД автора
      this.bdis=false      
    },

    selectChange(){     //включение кнопки при изменении ИД автора
      
      if ((this.cargo !=null)){ //если выбран ИД сотрудника, то обновляются данные, иначе - добавляются
        this.buttonText='Обновить информацию об имуществе с наименованием: '
        this.name=this.cargo.name
        this.id=this.cargo.id
        this.unit=this.cargo.unit
        
      }
      else{
        this.buttonText='Добавить имущество с наименованием: '
        this.id=null

      }
    }
  }

}
</script>
