insert into customer (account, nickname, password, address, phone_number)
values ('hohohoho', 'eden', 'a109e36947ad56de1dca1cc49f0ef8ac9ad9a7b1aa0df41fb3c4cb73c1ff01ea', 'address', '01012345678');

insert into product (name, price, image_url)
values ('삼립호빵', 5000, 'https://m.bakingmon.com/web/product/big/20200604/ca6e09b022765e331140afd0f8f81cbc.jpg');

insert into product (name, price, image_url)
values ('마이쮸 복숭아', 500, 'http://www.thessan.com/shopimages/thessancom/0060020000042.jpg?1477553153');

insert into product (name, price, image_url)
values ('꼬깔콘', 1300, 'https://sitem.ssgcdn.com/05/90/32/item/0000008329005_i1_1200.jpg');

insert into product (name, price, image_url)
values ('[든든] 국내산 세척무 1개', 1750, 'https://cdn-mart.baemin.com/sellergoods/main/e2bd1641-7f1f-49ff-83df-8005a16d5e78.png');

insert into product (name, price, image_url)
values ('빅파이', 3000, 'http://www.thessan.com/shopimages/thessancom/0060020001432.jpg?1488531916');

insert into product (name, price, image_url)
values ('평양냉면', 5000, 'https://cdn-mart.baemin.com/sellergoods/bulk/20220519-162122/28147-main-01.jpg');

insert into cart_item(customer_id, product_id)
values (1, 1);

insert into cart_item(customer_id, product_id)
values (1, 2);
