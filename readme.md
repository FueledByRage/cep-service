# Endpoint

A aplicação apresenta apenas o endpoint POST /cep-service que recebe um json com um valor de CEP

```
    {
	    "cep": "48180-000"
    }
```
Caso o cep seja invalido uma mensagem de erro será enviada.

# Testes

Há testes unitarios para o serviço em 

src\test\java\com\example\cepservice\unit

E testes de integração usando cucumber em 

src\test\java\com\example\cepservice\cucumber