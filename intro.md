# Giới thiệu

Chào mừng các bạn đến với con dường lập trình viên **backend** trong hệ thống thương mại điện tử (**eCommerce**)! Đây không chỉ là nơi bạn học cách viết **code**, mà còn là nơi bạn hiểu rõ cách **architect** những hệ thống **scalable**, mạnh mẽ và có thể phục vụ hàng triệu người dùng. Tại đây, chúng ta sẽ cùng khám phá và làm chủ 3 ngôn ngữ lập trình: **Node.js**, **Golang**, và **Java** 1 trong những ngôn ngữ giúp định hình nền tảng công nghệ hiện đại.

Trong khóa học này, chúng ta sẽ tập trung chủ yếu vào **Node.js**, nhưng không chỉ dừng lại ở đó. Bạn sẽ có cơ hội tìm hiểu cách kết hợp với **Golang** và **Java** để xây dựng hệ thống **backend** đa dạng và mạnh mẽ. Mục tiêu của khóa học là bạn không chỉ hiểu cách lập trình mà còn biết cách **build**, **maintain**, và tối ưu hóa toàn bộ hệ thống **backend** cho các ứng dụng thương mại điện tử phức tạp.

## Bạn sẽ học được gì?

Khóa học này sẽ mang đến cho bạn kiến thức và kỹ năng cần thiết để phát triển hệ thống **eCommerce** toàn diện. Dưới đây là những chủ đề chính mà chúng ta sẽ đi qua, với **Node.js** là tâm điểm, cùng với các công nghệ liên quan như **MySQL**, **MongoDB**, **Redis**,**kafka** và **RabbitMQ**. Bên cạnh đó còn có áp dụng mô hình nested comment và các hệ thống notification.

## Node.js:

### Khởi động dự án với Node.js:

- Cấu trúc thư mục và **packages** cơ bản.
- Cách kết nối **MongoDB** qua **Mongoose**.
- Sử dụng **JWT** để bảo mật phiên đăng nhập người dùng.

### Xây dựng API:

- Triển khai hệ thống **đăng ký, đăng nhập**, và quản lý phiên làm việc cho shop.
- Thiết kế **Middleware** để xử lý **API Key** và quyền truy cập.
- Xử lý **ErrorHandler** trong API và chuẩn hóa phản hồi API bằng **class**.

### Quản lý sản phẩm và đơn hàng:

- Tạo **schema** cho sản phẩm, xây dựng API quản lý sản phẩm.
- Sử dụng **Factory Pattern** để tối ưu hóa quá trình tạo sản phẩm.
- Xây dựng **Cart Service**, xử lý việc Thêm,Tăng/Giảm v.v sản phẩm trong giỏ hàng.

### Triển khai Hệ thống Nested Comments trong shop dev

**Nested Comments** : Nodejs + MongoDB

### RabbitMQ & Kafka - Message Queue:

- Tìm hiểu cơ chế Push/Pull trong message queue (task notification system)
- Tìm hiểu về **Kafka/Rabbit Message Queue**
- Xử lý các lỗi và quản lý **TTL** (Time To Live).
- Sử dụng **Pub/Sub** để đảm bảo thứ tự và sự toàn vẹn của **message** trong hệ thống **microservices**.

### Triển khai với AWS:

- Tích hợp **Amazon S3** để lưu trữ dữ liệu, thiết lập bảo mật với **CloudFront**.
- Sử dụng **EC2** và triển khai **CI/CD** từ **GitHub** để đảm bảo quá trình phát triển mượt mà.

## MySQL:

### Cơ bản về MySQL:

- Cách kết nối **MySQL** với **Node.js** và quản lý cơ sở dữ liệu.
- Tìm hiểu về **Master-Slave Setup** để đảm bảo dữ liệu luôn đồng bộ và nhanh chóng.
- Thực hành với 10 triệu **records** để làm quen với khối lượng dữ liệu lớn.

### Quản lý dữ liệu và tối ưu hóa:

- Áp dụng **Partition Database** để phân chia và quản lý dữ liệu hiệu quả theo các cấp độ từ cơ bản đến nâng cao.
- Triển khai **indexing** để tối ưu hóa truy vấn dữ liệu lớn.

### Roles & Permissions:

- Xây dựng hệ thống phân quyền chi tiết trong **MySQL**, triển khai các cấp quyền khác nhau cho người dùng và quản trị viên.

### MySQL nâng cao:

- Tìm hiểu về việc quản lý quyền và vai trò cho người dùng (**Roles & Permission**).
- Làm quen với **Partition Database** và **Indexing** để tối ưu hóa hiệu năng khi làm việc với dữ liệu lớn.

## Redis:

### Cấu trúc dữ liệu trong Redis:

- Tìm hiểu các loại dữ liệu như **Strings**, **Hashes**, **Lists**, **Sets**, và **Sorted Sets**.

### Redis Transaction và Pub/Sub:

- Sử dụng **Transaction** trong **Redis** để đảm bảo tính toàn vẹn dữ liệu.
- Triển khai **Pub/Sub** để thông báo theo thời gian thực.

## RabbitMQ & Kafka:

### Khởi tạo hệ thống Message Queue:

- Cài đặt **RabbitMQ** và **Kafka** cho hệ thống **microservices**.
- Hiểu cách xử lý lỗi khi gặp sự cố về **message queue** hoặc **server** die.

### Message Ordering và TTL:

- Đảm bảo tính toàn vẹn và thứ tự của **message** trong hệ thống với **Pub/Sub** và **Message Ordering**.
- Xử lý **TTL** trong **message queue** để tối ưu quản lý **message**.

## AWS Timeline:

### Làm quen với AWS:

- Giới thiệu về các dịch vụ của **AWS** và cách tích hợp vào hệ thống **backend**.
- Thiết lập **S3 Bucket** để lưu trữ dữ liệu bảo mật.

### Triển khai EC2 và CloudFront:

- Xây dựng hệ thống trên **EC2**, tối ưu quá trình bảo mật và quản lý dữ liệu với **CloudFront**.
- Kết nối với **GitHub** để thiết lập **CI/CD**, tự động hóa quá trình triển khai.
