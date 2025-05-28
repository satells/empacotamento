<h1>API EMPACOTAMENTO</h1>


<p>Esta API foi projetada para otimizar a forma como os itens de um pedido são empacotados, escolhendo a melhor caixa para os itens de um pedido.</p>

<p></p>

Utilizada a biblioteca [3d-bin-container-packing](https://github.com/skjolber/3d-bin-container-packing) que empacota caixas retangulares 3D em um container.

<h3>JSON de entrada<br></h3>


<span style="background-color: lightblue;"><code>{
"pedidos": [
    {
      "pedido_id": 101,
      "produtos": [
        {
          "produto_id": "PROD-ABC-001",
          "dimensoes": {
            "altura": 10,
            "largura": 5,
            "comprimento": 2
          }
        }
      ]
    },
    {
      "pedido_id": 102,
      "produtos": [
        {
          "produto_id": "PROD-XYZ-002",
          "dimensoes": {
            "altura": 15,
            "largura": 8,
            "comprimento": 3
          }
        }
      ]
    }
  ]
}
</code>
</span>

Para outros detalhes da API, visitar a documentação <code>http://host:8080/swagger-ui.html</code>



<h2>Links da app</h2>

<h3>Open Api</h3>
<code>http://host:8080/v3/api-docs</code>

<h3>Swagger</h3>
<code>http://host:8080/swagger-ui.html</code>

<h3>Prometheus</h3>
<code>http://host:9090</code>

<h3>Actuator</h3>
<code>http://host:8080/actuator</code>

<h3>Docker Compose</h3>

<p>Para iniciar no Docker Compose</p>
<code>docker compose up -d --build</code>


