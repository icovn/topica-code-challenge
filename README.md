# Bài thi
## Bài 1: 
- Tích hợp Google/FB Authentication cho công nghệ mình biết
- Yêu cầu: 
  + B1: Vào trang chủ, hệ thống hiển thị chưa đăng nhập
  + B2: Click “Đăng nhập" → chuyển người dùng sang trang login của Google/Facebook
  + B3: Đăng nhập ở Google/Facebook → chuyển người dùng về lại hệ thống với thông tin Google/Facebook xác minh được (vd: tên, email, …)
- Bonus (làm sẽ có điểm cộng - 5đ)
  + Chỉ cho phép người dùng có email @topica.edu.vn đăng nhập hệ thống
- Gợi ý: 
  + https://developers.google.com/api-client-library/
  + https://developers.facebook.com/docs/facebook-login/web

## Bài 2: 
- Xác thực 2 hệ thống cùng công nghệ (vd: cùng PHP, cùng Ruby)
- Yêu cầu:
  + B1: Vào ứng dụng #1 & #2, cả 2 hệ thống hiển thị chưa đăng nhập
  + B2: Đăng nhập ở hệ thống #1
  + B3: Click link trên hệ thống #1 → chuyển người dùng sang hệ thống #2 với trạng thái đã đăng nhập
  + B4: Click các link khác trên hệ thống #2 vẫn thể hiện đang ở trạng thái đăng nhập
- Bonus (làm sẽ có điểm cộng - 5đ)
  + Ở B3 thay vì click link trên hệ thống #1 sẽ gõ trang chủ của hệ thống #2, khi đó nếu người dùng được đăng nhập thì đạt
- Gợi ý:
  + Truyền thông tin user từ hệ thống #1 qua hệ thống #2 bằng URL ở B3
  + Sử dụng chung session name, session storage

## Bài 3:
- Xác thực 2 hệ thống khác công nghệ, login ở 1 hệ thống
- Yêu cầu: Tương tự “Bài 2” nhưng hai ứng dụng #1, #2 xây dựng bằng 2 công nghệ khác nhau (vd: PHP& Java, Java & NodeJS, …)
  + B1: Vào ứng dụng #1 & #2, cả 2 hệ thống hiển thị chưa đăng nhập
  + B2: Đăng nhập ở hệ thống #1
  + B3: Click link trên hệ thống #1 → chuyển người dùng sang hệ thống #2 với trạng thái đã đăng nhập
  + B4: Click các link khác trên hệ thống #2 vẫn thể hiện đang ở trạng thái đăng nhập
- Bonus (làm sẽ có điểm cộng - 5đ)
  + Ở B3 thay vì click link trên hệ thống #1 sẽ gõ trang chủ của hệ thống #2, khi đó nếu người dùng được đăng nhập thì đạt

## Bài 4:
- Xác thực 2 hệ thống khác công nghệ, login ở cả 2 hệ thống 
- Yêu cầu: Tương tự “Bài 3” nhưng thêm phần login bằng ứng dụng #2 và vào ứng dụng #1
  + B1: Vào ứng dụng #1 & #2, cả 2 hệ thống hiển thị chưa đăng nhập
  + B2: Đăng nhập ở hệ thống #1
  + B3: Click link trên hệ thống #1 → chuyển người dùng sang hệ thống #2 với trạng thái đã đăng nhập
  + B4: Click các link khác trên hệ thống #2 vẫn thể hiện đang ở trạng thái đăng nhập
  + B5: Logout ở cả 2 hệ thống #1, #2
  + B6: Vào ứng dụng #1 & #2, cả 2 hệ thống hiển thị chưa đăng nhập
  + B7: Đăng nhập ở hệ thống #2
  + B8: Click link trên hệ thống #2 → chuyển người dùng sang hệ thống #1 với trạng thái đã đăng nhập
  + B9: Click các link khác trên hệ thống #1 vẫn thể hiện đang ở trạng thái đăng nhập
- Bonus (làm sẽ có điểm cộng - 5đ)
  + Ở B3 thay vì click link trên hệ thống #1 sẽ gõ trang chủ của hệ thống #2, khi đó nếu người dùng được đăng nhập thì đạt
  + Ở B5 nếu logout ở 1 hệ thống, hệ thống còn lại cũng logout thì đạt
  + Ở B8 thay vì click link trên hệ thống #2 sẽ gõ trang chủ của hệ thống #1, khi đó nếu người dùng được đăng nhập thì đạt


# Code 
- php: done
- python: todo
- nodejs: todo
- ruby: todo

