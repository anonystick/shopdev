---
title: JAVA 21 - DDD và Hệ thống bảo vệ đầu tiên khi mở bán vé ngày đâu tiên
description: Ở các chương trước chúng ta đã biết cách setup một dự án với kiến trúc DDD, với các module đầy đủ như xxxx-domain, xxxx-application, xxxx-infrastructure, xxxx-api. Và trong mỗi module đều có sự quản lý của file pom.xml.
---

Ở các chương trước chúng ta đã biết cách setup một dự án với kiến trúc DDD, với các module đầy đủ như xxxx-domain, xxxx-application, xxxx-infrastructure, xxxx-api. Và trong mỗi module đều có sự quản lý của file `pom.xml`. Và tiếp theo dự án vetautet.dev sẽ bắt đầu tiến hành xây những kiến trúc để chống chọi với quân xâm lược thế nào? 

## DDD - Kịch bản hệ thống tấn công.

Tôi đã tạo ra một kịch bản thực tế như hình ảnh và code, các bạn cũng có thể xem trình diễn thực tế tại đây: [DDD - Hệ thống bảo vệ đầu tiên khi mở bán vé ngày đâu tiên]() với các thao tác hoàn chỉnh.
Trước tiên hãy để tôi kể câu chuyện kịch bản thực tế thế này. Ngày 01/11 chúng tôi bắt đầu mở bán vé tàu SIÊU TỐC (ddd) 350km/hour. Và chúng tôi không định lượng được lượng người mua vé (user) lại có thể nhiều đến thế. 

Kịch bản: Trong dự án DDD hệ thống chúng tôi chỉ (giả sử, hay giả lập) tối đa là 10 threads (được thiết lập trong application.yml) nghĩa là Tomcat chỉ có thể xử lý tối đa 10 yêu cầu (requests) đồng thời tại bất kỳ thời điểm nào. Nhưng khi mở bán thì lượng user cực khủng gấp 5 lần như vậy nghĩa là 50 threads trong Thread Group. Điều này dẫn đến việc Tomcat chỉ có thể xử lý tối đa 10 yêu cầu đồng thời, còn 40 yêu cầu khác sẽ phải chờ trong hàng đợi hoặc bị từ chối nếu hàng đợi đầy. Và tất nhiên khi số lượng yêu cầu vượt quá khả năng xử lý của Tomcat, các yêu cầu bị trễ hoặc thất bại.

Cấu hình tomcat trong dự án (trích mã nguồn dự án):

```bash
tomcat:
threads:
    max: 10 # xử lý tối đa 10 yêu cầu (requests) đồng thời
```

Api bán vé ngày 01/11

```java
```

Chú ý rằng, trong api chúng tôi có liên kết một số đơn vị dịch vụ khác bên ngoài, gọi là third-party ví dụ là cung cấp thức ăn nhanh...

Cấu hình user ồ ạt vào mua vé:

Hình ảnh jmeter:

Hậu quả là hệ thống tế liệt như hình được lấy từ kịch bản thực tế:

Hình ảnh jmeter:

khách hàng bắt đầu phàn nàn... 


Hình ảnh comment.

## DDD - Xây dựng hệ thống bảo vệ đầu tiên.

Như vậy là kịch bản dựng lên đã thất bại ngay từ ngày đầu bán vé. Đội ngũ R/D của chúng tôi quay lại và tiếp tục nghiên cứu. Và có những cách khắc phục như sau. 
Đầu tiên chúng tôi **Tăng số lượng thread tối đa của Tomcat** lên tận 50 threads. Nhưng một trong số nhân viên lập trình lại có ý kiến rằng, chúng ta không thể làm như vậy mãi được, vì ngày mai có thể là 
70, 100, 200. Làm sao chúng ta biết trước mà tăng. Vậy cách này chỉ là tạm thời. 

Sau một hồi suy nghĩ. Chỉ có một cách đó là giảm tấn suất truy cập của User và hệ thống mua vé. Đúng vậy, phân tích tiếp. 
Hệ thống bán vé của chúng tôi, tạo event ngày 01/11 chỉ có bán ra 1k tickets. Như vậy nó sẽ đáp ứng với 1K user mua vé, như vạy nếu 100K users tấn công vào để làm gì, vì sẽ có 99K user sẽ không mua được vé. 
Như vậy vô nghĩa hay sao. 

```
if(ticker <= 0){
    return // Không cho vào mua nữa, hết vé
}
// 99K còn lại sẽ được chặn lại
```

Phương pháp này có thể triển khai, như vậy thông qua các phân tích trên chúng ta có những keyword module để làm việc. 

+ CircuitBreaker
+ RateLimiter
+ Retry
+ TimeLimiter

## DDD - hệ thống bảo vệ lượng request lớn đã áp dụng.

Trong kịch bản bán vé cho ngày 01/11 với 1000 vé và 100.000 người dùng truy cập đồng thời, chúng ta có thể sử dụng các module để bảo vệ hệ thống khỏi quá tải, đảm bảo hiệu suất và tính ổn định. Tôi sẽ giải thích cơ bản ở đây. Còn code thì hãy xem ở đây: [Vé tàu mở bán thành công mặc dù 100K Users tấn công]()

1. CircuitBreaker

Sử dụng: CircuitBreaker sẽ bảo vệ hệ thống khỏi các yêu cầu dư thừa khi dịch vụ quá tải hoặc gặp lỗi, giảm áp lực lên server.

Thực hiện code trên SpingBoot 3.x:
Cấu hình CircuitBreaker để kích hoạt khi tỷ lệ lỗi vượt quá một ngưỡng nhất định, chẳng hạn 50% trong một khoảng thời gian ngắn.
Khi ngưỡng lỗi đạt đến mức này, CircuitBreaker chuyển sang trạng thái “Open” (mở), ngừng xử lý yêu cầu mới, giúp hệ thống giảm tải.
Sau một khoảng thời gian nghỉ, CircuitBreaker sẽ chuyển về “Half-Open” để thử lại, nếu thành công, hệ thống sẽ trở lại hoạt động bình thường.

Kết quả: Bảo vệ các thành phần quan trọng của hệ thống khỏi sụp đổ khi gặp phải số lượng lớn các yêu cầu thất bại.

2. RateLimiter

Sử dụng: RateLimiter giới hạn số lượng yêu cầu được chấp nhận từ mỗi người dùng trong một khoảng thời gian xác định.

Thực hiện code trên SpingBoot 3.x:
Đặt giới hạn tối đa, ví dụ, cho phép một người dùng thực hiện tối đa 1 yêu cầu mỗi giây.
Trong bối cảnh có 100.000 người dùng, RateLimiter sẽ ngăn không cho tất cả yêu cầu cùng lúc tiến vào hệ thống, chỉ những yêu cầu trong giới hạn mới được phép xử lý.

Kết quả: Giảm thiểu tình trạng tấn công DDoS từ các yêu cầu liên tục của một người dùng, tăng khả năng công bằng để nhiều người dùng có thể truy cập.

3. Retry

Sử dụng: Retry hỗ trợ việc thử lại các yêu cầu nếu chúng thất bại do lỗi tạm thời hoặc lỗi không ổn định (chẳng hạn như timeout).

Thực hiện code trên SpingBoot 3.x:
Cấu hình số lần retry, ví dụ giới hạn ở 2-3 lần với khoảng thời gian chờ giữa các lần thử.
Trong trường hợp lỗi kết nối hoặc yêu cầu gặp timeout, Retry sẽ thử lại, giúp tăng cơ hội thành công mà không làm gián đoạn quá trình mua vé.

Kết quả: Giảm thiểu tình trạng người dùng mất cơ hội mua vé do lỗi tạm thời, tăng trải nghiệm người dùng.

4. TimeLimiter

Sử dụng: TimeLimiter kiểm soát thời gian tối đa để xử lý một yêu cầu, giúp tránh việc một yêu cầu chiếm giữ tài nguyên quá lâu.

Thực hiện code trên SpingBoot 3.x:
Đặt giới hạn thời gian xử lý, ví dụ 2 giây cho mỗi yêu cầu.
Nếu quá thời gian cho phép, yêu cầu sẽ bị cắt, và hệ thống phản hồi lại cho người dùng với thông báo timeout.

Kết quả: Giảm thiểu tình trạng tắc nghẽn tài nguyên, giúp hệ thống vẫn có thể phục vụ các yêu cầu khác ngay cả khi có quá tải.

hệ thống đã đảm bảo chưa... Chưa, chúng ta chưa biết rằng sự nguy hiểm mới đang dần tới mà chúng ta không hế hay biết. Chương 22 sẽ nói tới và khắc phục..

## Tóm tắt và một sự nguy hiểm đang tiến dần đến.

Kết hợp các module này giúp hệ thống chịu tải tốt hơn khi đối mặt với lưu lượng truy cập cao. Với CircuitBreaker và RateLimiter, hệ thống được bảo vệ khỏi số lượng lớn yêu cầu đổ vào cùng lúc. Retry và TimeLimiter đảm bảo rằng các yêu cầu thất bại hoặc quá chậm không gây ra tình trạng nghẽn tắc kéo dài trong hệ thống.