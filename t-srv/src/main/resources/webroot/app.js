var eb = new vertx.EventBus('http://localhost:8080/eventbus');

eb.onopen = function() {

  eb.send('test.test', {name: 'tim', age: 587}, function(result) { 
      console.log(result);
      document.getElementById('testid').innerHTML=JSON.stringify(result)});

}
