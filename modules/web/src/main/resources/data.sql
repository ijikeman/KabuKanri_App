-- 所有者
INSERT INTO owner(name) VALUES
('Father'),
('Mother'),
('Brother'),
('Sister');

-- 証券会社
INSERT INTO broker(name) VALUES
('マネックス証券'),
('SBI証券'),
('SBIネオトレード'),
('みずほ証券'),
('UFJモルガンスタンレー証券'),
('au株');

-- 銘柄種別
INSERT INTO sector(name) VALUES
('水産・農林業'), -- 1
('食料品'), -- 2
('鉱業'), -- 3
('石油・石炭製品'), -- 4
('建設業'), -- 5
('金属製品'), -- 6
('ガラス・土石製品'), -- 7
('繊維製品'), -- 8
('パルプ・紙'), -- 9
('化学'), -- 10
('医薬品'), -- 11
('ゴム製品'), -- 12
('輸送用機器'), -- 13
('鉄鋼'), -- 14
('非鉄金属'), -- 15
('機械'), -- 16
('電気機器'), -- 17
('精密機器'), -- 18
('その他製品'), -- 19
('情報・通信業'), -- 20
('サービス業'), -- 21
('電気・ガス業'), -- 22
('陸運業'), -- 23
('海運業'), -- 24
('空運業'), -- 25
('倉庫・運輸関連業'), -- 26
('卸売業'), -- 27
('小売業'), -- 28
('銀行業'), -- 29
('証券、商品先物取引業'), -- 30
('保険業'), -- 31
('その他金融業'), -- 32
('不動産業'); -- 33

-- 銘柄
INSERT INTO stock(code, name, country, current_price, latest_dividend, earnings_date, last_updated, sector_id) VALUES
('9142', '九州旅客鉄道', 'jp', NULL, NULL, NULL, NULL, 25),
('3563', 'FOOD & LIFE COMPANIES', 'jp', NULL, NULL, NULL, NULL, 22),
('9434', 'ソフトバンク', 'jp', NULL, NULL, NULL, NULL, 20),
('4765', 'ＳＢＩグローバルアセットマネジメント', 'jp', NULL, NULL, NULL, NULL, 20),
('8473', 'ＳＢＩホールディングス', 'jp', NULL, NULL, NULL, NULL, 20),
('3763', 'プロシップ', 'jp', NULL, NULL, NULL, NULL, 20),
('2193', 'クックパッド', 'jp', NULL, NULL, NULL, NULL, 20),
('3548', 'バロックジャパンリミテッド', 'jp', NULL, NULL, NULL, NULL, 20),
('9201', '日本航空', 'jp', NULL, NULL, NULL, NULL, 20),
('5621', 'ヒューマンテクノロジーズ', 'jp', NULL, NULL, NULL, NULL, 20),
('4704', 'トレンドマイクロ', 'jp', NULL, NULL, NULL, NULL, 20),
('9449', 'ＧＭＯインターネットグループ', 'jp', NULL, NULL, NULL, NULL, 20),
('4475', 'HENNGE', 'jp', NULL, NULL, NULL, NULL, 20),
('9702', 'アイ・エス・ビー', 'jp', NULL, NULL, NULL, NULL, 20),
('3837', 'アドソル日進', 'jp', NULL, NULL, NULL, NULL, 20),
('6458', '新晃工業', 'jp', NULL, NULL, NULL, NULL, 20),
('8002', '丸紅', 'jp', NULL, NULL, NULL, NULL, 20),
('9757', '船井総研ホールディングス', 'jp', NULL, NULL, NULL, NULL, 20),
('7374', 'コンフィデンス・インターワークス', 'jp', NULL, NULL, NULL, NULL, 20),
('9765', 'オオバ', 'jp', NULL, NULL, NULL, NULL, 20),
('4481', 'ベース', 'jp', NULL, NULL, NULL, NULL, 20),
('3993', 'PKSHA Technorogy', 'jp', NULL, NULL, NULL, NULL, 20),
('4071', 'プラスアルファコンサルティング', 'jp', NULL, NULL, NULL, NULL, 20),
('2432', 'DeNa', 'jp', NULL, NULL, NULL, NULL, 20);
('3923', 'ラクス', 'jp', NULL, NULL, NULL, NULL, 20);