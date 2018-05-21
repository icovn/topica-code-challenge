# Code demo
- php: laravel
- python: http://flask.pocoo.org/
- nodejs: 
- ruby: https://rubyonrails.org/


# PHP

## Yêu cầu 
PHP 7.2 & composer

## Các bước thực hiện
Cài đặt Redis extension cho PHP 
```console
sudo apt-get install php-redis
```

Vào folder web1 rồi chạy
```console
php -S localhost:8001 
```

Vào folder web2 rồi chạy
```console
php -S localhost:8002
```

Kiểm tra redis
```console
redis-cli
keys *
type PHPREDIS_SESSION:4f809531b94004b3d70bee057508862f
GET PHPREDIS_SESSION:4f809531b94004b3d70bee057508862f
```

