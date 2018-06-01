# PHP

## Yêu cầu 
- PHP 7.2 
- Composer
- Redis

## Các bước thực hiện

### Cài đặt môi trường PHP
```console
sudo apt-get install python-software-properties
sudo add-apt-repository ppa:ondrej/php
sudo apt-get update
sudo apt-get install php7.2-cli php7.2-fpm php7.2-curl php7.2-gd php7.2-mysql php7.2-mbstring zip unzip
sudo apt-get install php7.2-mbstring
sudo apt-get install php7.2-dom
```

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

Vào folder php-framework/laravel rồi chạy
```console
php artisan serve --port 8001
```

Vào folder php-framework/silex rồi chạy
```console
php -S 0.0.0.0:8002 -t app/web
```

### Cấu hình Nginx
```
server {
    listen 80;
    server_name sso-laravel.icovn.me;
    root /path-to-laravel/public;

    add_header X-Frame-Options "SAMEORIGIN";
    add_header X-XSS-Protection "1; mode=block";
    add_header X-Content-Type-Options "nosniff";

    index index.html index.htm index.php;

    charset utf-8;

    location / {
        try_files $uri $uri/ /index.php?$query_string;
    }

    location = /favicon.ico { access_log off; log_not_found off; }
    location = /robots.txt  { access_log off; log_not_found off; }

    error_page 404 /index.php;

    location ~ \.php$ {
        fastcgi_split_path_info ^(.+\.php)(/.+)$;
        fastcgi_pass unix:/var/run/php/php7.2-fpm.sock;
        fastcgi_index index.php;
        fastcgi_param SCRIPT_FILENAME $document_root/$fastcgi_script_name;
        include fastcgi_params;
    }

    location ~ /\.(?!well-known).* {
        deny all;
    }
}
```

# NodeJS

## Yêu cầu
- NodeJS 8.x

## Các bước thực hiện

### Cài đặt môi tường NodeJS
```console
sudo apt-get install build-essential libssl-dev
curl -sL https://raw.githubusercontent.com/creationix/nvm/v0.33.8/install.sh -o install_nvm.sh
bash install_nvm.sh
source ~/.bash_profile 
nvm ls-remote
nvm install 8.11.2
nvm use 8.11.2
node -v
```