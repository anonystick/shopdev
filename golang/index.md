---
outline: deep
---

<script setup>
import {
  VPTeamPage,
  VPTeamPageTitle,
  VPTeamMembers
} from 'vitepress/theme'

const members = [
  {
    avatar: 'https://avatars.githubusercontent.com/u/48472372?v=4',
    name: 'Anonystick',
    title: 'Creator',
    links: [
      { icon: 'github', link: 'https://github.com/anonystick' },
      { icon: 'blog', link: 'https://anonystick.com' }
    ]
  },
  {
    avatar: 'https://avatars.githubusercontent.com/u/129947921?v=4',
    name: 'JunYue',
    title: 'Creator',
    links: [
      { icon: 'github', link: 'https://github.com/vietgs03' }
    ]
  },
]
</script>

<VPTeamPage>
  <VPTeamPageTitle>
    <template #title>
      DEVs tham gia phát triển dự án Golang Documentation API
    </template>
    <template #lead>
      Chịu trách nhiệm các bài giảng về Go Backend
    </template>
  </VPTeamPageTitle>
 <VPTeamMembers size="small" :members="members" />
</VPTeamPage>

# Go Backend - Giới thiệu về dự án ShopDEVGO

Khi nói đến việc phát triển ứng dụng web, ngôn ngữ lập trình Go (hay Golang) đã nhanh chóng trở thành lựa chọn hàng đầu cho nhiều công ty công nghệ. Go được thiết kế bởi Google với cú pháp đơn giản và hiệu suất cao, giúp việc phát triển và bảo trì ứng dụng trở nên dễ dàng hơn.

Một trong những lý do chính khiến ShopDEVGO chọn Go là hiệu suất. Go biên dịch trực tiếp thành mã máy, mang lại tốc độ thực thi nhanh hơn so với nhiều ngôn ngữ khác như Java hay Node.js. Điều này có nghĩa là ứng dụng có thể xử lý hàng nghìn kết nối đồng thời mà không gặp phải tắc nghẽn, điều này đặc biệt quan trọng trong môi trường thương mại điện tử.

So với Java, Go mang lại hiệu suất tốt hơn với cú pháp đơn giản hơn, giúp đội ngũ phát triển tiết kiệm thời gian hơn trong việc triển khai và bảo trì. Java có ưu điểm về tính ổn định và khả năng tương thích đa nền tảng, nhưng thường gặp phải độ phức tạp cao hơn. Còn với Node.js, trong khi Node lý tưởng cho các ứng dụng bất đồng bộ, Go vượt trội hơn về khả năng xử lý đồng thời, giúp tăng cường hiệu suất cho các dịch vụ đòi hỏi tài nguyên lớn.

Nhiều công ty lớn như Google, Dropbox và Uber đã chọn Go cho các dịch vụ của họ. Sự hỗ trợ từ các tập đoàn này đã làm tăng sức hút của Go trong cộng đồng lập trình viên. Hệ sinh thái phong phú với nhiều thư viện và công cụ hỗ trợ giúp cho việc phát triển ứng dụng trở nên thuận tiện và hiệu quả hơn.

Trong tương lai, nhu cầu về lập trình viên Go sẽ tiếp tục gia tăng khi ngày càng nhiều công ty chuyển sang xây dựng các ứng dụng vi mô và dịch vụ có khả năng xử lý đồng thời tốt hơn. Cộng đồng lập trình viên Go đang phát triển mạnh mẽ, cung cấp nhiều tài nguyên học tập và hỗ trợ, tạo điều kiện thuận lợi cho các lập trình viên muốn tìm kiếm cơ hội việc làm trong lĩnh vực này.

Dự án ShopDEVGO không chỉ là một ứng dụng thương mại điện tử, mà còn là cơ hội để bạn trải nghiệm và phát triển với một trong những ngôn ngữ lập trình tiềm năng nhất hiện nay. Hãy tham gia cùng chúng tôi để khám phá sức mạnh của Go và xây dựng tương lai của thương mại điện tử!

## Dành cho ai?

**Backend Developers muốn chuyển đổi sang Go** :

- Đã có sẵn kiến thức nền, kiến thức cơ bản về ngôn ngữ Go.
- Đã coi qua các video về Nodejs ở hội viên hoặc hơn hết đã hiểu biết về Java, Python,v.v.

**Go Enthusiasts** :

- Những người có đam mê và có kinh nghiệm cơ bản về Go, muốn đào sâu hơn vào việc sử dụng Go trong hệ thống **eCommerce** Và **Microservice**.

## Học được gì?

Trong hệ thống `Con đường tiến tới Go`, chúng ta sẽ đi qua hai chặng đường:

1. **Chặng một** (Dành cho level 0, 1 của Go): Bằng cách nghiên cứu bài viết và xem video thực hành, bạn sẽ hình thành hệ thống kiến thức cốt lõi của ngôn ngữ Go. Cụ thể, bạn sẽ hiểu biết về cú pháp cơ bản và các tính năng quan trọng trong Go, bao gồm:

   - Functions.
   - Struct.
   - Interfaces.
   - Packages.
   - Concurrency.
   - Reflection.

   Trong mỗi phần, chúng ta sẽ học từ những kiến thức cơ bản qua các bài viết và video thực hành. Bạn sẽ hiểu cách sử dụng Go trong các tình huống thực tế, cùng với việc giải thích cách dùng tốt nhất cho từng trường hợp.

2. **Chặng 2 phần nâng cao** (Dành cho level > 2 của Go): Ở giai đoạn này, bạn sẽ tiếp cận mọi công nghệ backend kết hợp với Go để xây dựng một dự án eCommerce thực tế ở **Cấp độ doanh nghiệp**. Bạn sẽ học cách sử dụng các công cụ và công nghệ hiện đại như:
   - **Redis**: Tìm hiểu và thiết lập Redis sentinel hoặc Cluster để đảm bảo tính sẵn sàng cao.
   - **Mysql**: Tìm hiểu Mysql trong các hệ thống lớn với việc sử dụng Pool connection, tối ưu hóa các câu truy vấn SQL, và áp dụng kỹ thuật partitioning, sharding để xử lý dữ liệu quy mô lớn.
   - **Docker**: Học cách tạo container cho ứng dụng Go, thiết lập môi trường phát triển sử dụng Docker Compose để quản lý nhiều dịch vụ như Redis, MySQL, Kafka cùng một lúc.
   - **Kafka**.
   - **Gin Framework**: Xây dựng API Restful với Gin, quản lý logging, xử lý lỗi, và Test Mocking.
   - Triển khai hệ thống API phức tạp.
   - Xử lý cơ sở dữ liệu quy mô lớn và các hệ thống phân tán (distributed systems).
