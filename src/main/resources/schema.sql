DROP TABLE IF EXISTS RESTAURANT;
CREATE TABLE RESTAURANTS(
restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
restaurant_name VARCHAR(50) NOT NULL,
restaurant_address VARCHAR(50),
restaurant_cuisine VARCHAR(50)
);

DROP TABLE IF EXISTS FOOD;
CREATE TABLE FOOD(
food_id INT AUTO_INCREMENT PRIMARY KEY,
food_name VARCHAR(50) NOT NULL,
food_diet VARCHAR(50),
food_rating INT,
restaurant_id INT,
FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS(restaurant_id)
);