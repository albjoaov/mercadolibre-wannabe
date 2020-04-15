INSERT INTO user (id, created_at, email, password, updated_at, version)
VALUES ('17f59f23-80ad-42c9-ad9b-d70c9a382896', '2020-04-11 07:28:25.707581000', 'example@example.com',
        '$2a$10$NIE5bBN/P2q5m64SuTCwteiNXqHYXmLSvm7K/mRwjV854A1zupEkq', '2020-04-11 07:28:25.707581000', 0);

INSERT INTO mercadolibrewannabe.category (id, created_at, name, updated_at, version, parent_category_id)
VALUES ('1ba82ded-ffdb-49b8-ae13-71db7d96c0eb', '2020-04-11 07:30:39.286600000', 'TESTE',
        '2020-04-11 07:30:39.286600000', 0, null);

INSERT INTO mercadolibrewannabe.product (id, amount, created_at, description, name, updated_at, value, version,
                                         category_id, user_id)
VALUES ('928b1616-cae5-4a08-8841-4a56e6fdda2f', 89, '2020-04-11 07:31:18.335457000', 'cccccccccccccccc', 'bbbbbbbbbb',
        '2020-04-11 07:31:20.938819000', 1000.80, 0, '1ba82ded-ffdb-49b8-ae13-71db7d96c0eb',
        '17f59f23-80ad-42c9-ad9b-d70c9a382896');

INSERT INTO mercadolibrewannabe.features (product_id, detail, name)
VALUES ('928b1616-cae5-4a08-8841-4a56e6fdda2f', 'detalhe1', 'nome1');
INSERT INTO mercadolibrewannabe.features (product_id, detail, name)
VALUES ('928b1616-cae5-4a08-8841-4a56e6fdda2f', 'detalhe2', 'nome2');
INSERT INTO mercadolibrewannabe.features (product_id, detail, name)
VALUES ('928b1616-cae5-4a08-8841-4a56e6fdda2f', 'detalhe3', 'nome3');

INSERT INTO mercadolibrewannabe.photo (product_id, url)
VALUES ('928b1616-cae5-4a08-8841-4a56e6fdda2f', 'https://s3.amazon.com/ponto.pnge256534a-c09f-4432-bccb-b712cab19ee3');
INSERT INTO mercadolibrewannabe.photo (product_id, url)
VALUES ('928b1616-cae5-4a08-8841-4a56e6fdda2f',
        'https://s3.amazon.com/carteira.png0b245640-5e17-4a4b-a485-76e296190137');

INSERT INTO mercadolibrewannabe.review (id, created_at, description, rating, title, updated_at, version, user_id,
                                        product_id)
VALUES ('7e4ad55a-58bc-47e9-90ed-da24cb8e85a4', '2020-04-11 07:32:12.881188000', 'Gerador de lero lero', 5,
        'Uma bela belezura', '2020-04-11 07:32:12.881188000', 0, '17f59f23-80ad-42c9-ad9b-d70c9a382896',
        '928b1616-cae5-4a08-8841-4a56e6fdda2f');



