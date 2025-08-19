-- ownerテーブル
CREATE TABLE IF NOT EXISTS owner (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- brokerテーブル
CREATE TABLE IF NOT EXISTS broker (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- sectorテーブル
CREATE TABLE IF NOT EXISTS sector (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- stockテーブル
CREATE TABLE IF NOT EXISTS stock (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(16) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(16) NOT NULL,
    current_price INT,
    latest_dividend REAL,
    earnings_date VARCHAR(255), -- MariaDBではTEXTよりVARCHARが一般的
    last_updated VARCHAR(255),
    sector_id INT NOT NULL,
    FOREIGN KEY(sector_id) REFERENCES sector(id)
);

-- transactionテーブル
CREATE TABLE IF NOT EXISTS transaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    owner_id INT NOT NULL,
    stock_id INT NOT NULL,
    broker_id INT NOT NULL,
    price_per_share INT NOT NULL,
    transaction_type VARCHAR(8),
    quantity INT NOT NULL,
    fee INT NOT NULL,
    transaction_at DATETIME, -- MariaDBの標準はDATETIME
    FOREIGN KEY(owner_id) REFERENCES owner(id),
    FOREIGN KEY(stock_id) REFERENCES stock(id),
    FOREIGN KEY(broker_id) REFERENCES broker(id)
);

-- dividend_and_preferentialsテーブル
CREATE TABLE IF NOT EXISTS dividend_and_preferentials (
    id INT PRIMARY KEY AUTO_INCREMENT,
    stock_id INT NOT NULL,
    dividenv_price INT NOT NULL,
    fee INT NOT NULL,
    transaction_at DATETIME,
    FOREIGN KEY(stock_id) REFERENCES stock(id)
);

-- holdingsテーブル
CREATE TABLE IF NOT EXISTS holdings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    owner_id INT NOT NULL,
    stock_id INT NOT NULL,
    quantity INT NOT NULL,
    average_price INT NOT NULL,
    total_dividend INT NOT NULL,
    total_preferential INT NOT NULL,
    updated_at DATETIME,
    FOREIGN KEY(owner_id) REFERENCES owner(id),
    FOREIGN KEY(stock_id) REFERENCES stock(id)
);
