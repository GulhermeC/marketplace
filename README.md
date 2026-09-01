# Marketplace de Produtos (Venda entre Utilizadores)
 
## Description
Plataforma onde utilizadores podem vender e comprar produtos usados/novos, com sistema de encomendas e gestão de stock por vendedor
 
## Main Entities (Data Base)
- **Utilizadores:** id, nome, contacto, localização (cidade/país), tipo (compradores/vendedores)
- **Produtos:** id, nome, preço, categoria, stock, peso, vendedor
- **Encomendas:** id, produtos, comprador, estado (pendente/enviado/entregue), preço total
 
## Main Business Rules
- Reduzir stock automaticamente após confirmação da compra e repor se a encomenda for cancelada
- Impedir compra se stock for zero