# Prática de JPA, DTO e Bean Validation
Exercício para práticar o conteúdo visto em aula.

## Endpoints

---

### POST /eventos
Cria um novo evento.

### GET /eventos
Retorna todos os eventos.

### GET /eventos/{id}
Retorna um evento específico.

### GET /eventos/gratuitos
Retorna todos os eventos gratuitos.

### GET /eventos/data?ocorrencia={ocorrencia}
Retorna todos os eventos que **ocorrem/ocorreram ou foram publicados** na data informada. Utilize RequestParam para receber a data.

### GET /eventos/periodo?inicio={inicio}&fim={fim}
Retorna todos os eventos que **ocorrem/ocorreram** no período informado. Utilize RequestParam para receber as datas.

### DELETE /eventos/{id}
Deleta um evento.

### PUT /eventos/{id}
Atualiza um evento. Você deve considerar:
- Nome e local são obrigatórios. 
- Se o evento estiver cancelado ou já tiver ocorrido, o pedido nao pode ser processado.

### PATCH /eventos/{id}/cancelamento
Cancela um evento. Você deve considerar:
- Se o evento já estiver cancelado ou já tiver ocorrido, o pedido nao pode ser processado.

## Modelos

---

### Entidade: Evento
```json
{
  "id": 1,
  "nome": "Evento 1",
  "local": "Local 1",
  "dataEvento": "2021-01-01",
  "gratuito": true,
  "cancelado": false
}
```

---
### DTO para criação de Evento
```json
{
  "nome": "Evento 1", // obrigatório, min 5, max 100
  "local": "Local 1", // obrigatório, min 5, max 200
  "dataEvento": "2021-01-01", // obrigatório, data futura ou presente
  "gratuito": true, // obrigatório
}
```

---
### DTO para atualização de Evento
```json
{
  "nome": "Evento 1", // obrigatório, min 5, max 100
  "local": "Local 1", // obrigatório, min 5, max 200
  "dataEvento": "2021-01-01", // não obrigatório, data futura ou presente
  "gratuito": true, // não obrigatório
}
```

---
### DTO de consulta:
```json
{
  "id": 2,
  "nome": "Teste evento 1",
  "local": "Teste Local 1",
  "dataEvento": "2024-04-01",
  "gratuito": false,
  "cancelado": false,
  "dataPublicacao": "2024-04-01",
  "status": "ABERTO" // Campo calculado
}
```

Os status possíveis são:
- ABERTO
- FINALIZADO
- CANCELADO

---
- ## As entidades não devem ser expostas diretamente pelos endpoints;
- ## As validações do Bean Validation devem ser aplicadas diretamente nas dtos.