import { defineConfig, HeadConfig } from "vitepress";

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "Shop Ecommerce Backend",
  description: "Con đường đi tới lập trình viên Backend",
  // header
  head: [
    [
      "meta",
      { name: "keywords", content: "java, golang, nodejs, backend, ecommerce" },
    ],
    ["link", { rel: "icon", href: "/favicon.ico" }],
  ],
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    //
    logo: "/logo.jpeg",
    // outline
    outline: {
      level: [2, 6],
      label: "label",
    },
    // docFooter
    docFooter: {
      prev: "bài trước",
      next: "tiếp theo",
    },
    darkModeSwitchLabel: "sáng/tối",
    returnToTopLabel: "lên đầu",
    search: {
      provider: "local",
    },
    footer: {
      message: "Released under the MIT License.",
      copyright: "Copyright © 2024-present Anonystick Golang",
    },
    lastUpdated: {
      text: "Cập nhật",
      formatOptions: {
        dateStyle: "full",
        timeStyle: "medium",
      },
    },
    nav: [
      { text: "Home", link: "/" },
      { text: "Docs", link: "/docs" },
      //       { text: "Go Backend", link: "/golang" },
      //       { text: "Java Backend", link: "/java" },
      //       { text: "Nodejs Backend", link: "/node" },
      //       { text: "Java Interview", link: "/interview" },
      {
        text: "Backend",
        items: [
          { text: "Java", link: "/java" },
          { text: "Nodejs", link: "/node" },
          { text: "Go", link: "/golang" },
        ],
      },
      {
        text: "Frontend",
        items: [
          { text: "Manager", link: "/manager" },
          { text: "Store", link: "/store" },
          { text: "Buyer", link: "/buyer" },
        ],
      },
      {
        text: "Interview",
        items: [
          { text: "Java", link: "/interview" },
          { text: "Nodejs", link: "/interview" },
          { text: "Go", link: "/interview" },
        ],
      },
    ],
    sidebar: {
      "/interview/": [
        {
          text: "Chuẩn bị cho phỏng vấn",
          collapsed: true,
          items: [
            {
              text: "Hướng dẫn từng bước cách chuẩn bị cho các cuộc phỏng vấn Java",
              link: "/interview/interview-preparation/preparation-01",
            },
          ],
        },
      ],
      "/golang/": [
        {
          text: "Golang Backend Ecommerce",
          items: [
            {
              text: "GOLANG cho người mới bắt đầu từ con số 0",
              link: "/golang/go-basic",
            },
            {
              text: "Go 29: [User Login Interface] - Registration",
              link: "/golang/go-29",
            },
            {
              text: "Go 30: [User Login Interface] - Triển khai Swagger, Open API",
              link: "/golang/go-30",
            },
            {
              text: "Go 31: [User Login Interface] - Verify OTP",
              link: "/golang/go-31",
            },
            {
              text: "Go 32: [User Login Interface] - Update Password Register",
              link: "/golang/go-32",
            },
            {
              text: "Go 33: [User Login Interface] - Login",
              link: "/golang/go-33",
            },
            {
              text: "Go 34: [User Login Interface] - Use Transaction in SQLC",
              link: "/golang/go-34",
            },
            {
              text: "Go 35: [User Login Interface] - Setup Two-Factor-Authentication Account",
              link: "/golang/go-35",
            },
            {
              text: "Go 36: [User Login Interface] - Enable Two-Factor-Authentication Account",
              link: "/golang/go-36",
            },
            {
              text: "Go 37: [User Login Interface] - Dislable Two-Factor-Authentication Account",
              link: "/golang/go-37",
            },
            {
              text: "Go 38: [Sysem Application] - RateLimit với 3 tầng ứng dụng (Tính khả dụng)",
              link: "/golang/go-38",
            },
            {
              text: "Go 39: [Product Interface] - Hệ thống hàng hóa, nhu cầu, kiến trúc...",
              link: "/golang/go-39",
            },
            // ... các mục khác
          ],
        },
      ],
      "/java/": [
        {
          text: "Java Backend Ecommerce",
          items: [
            {
              text: "Java 01: JAVA được phát triển như thế nào?",
              link: "/java/java-01",
            },
            {
              text: "JAVA cho người mới bắt đầu từ con số 0",
              link: "/java/java-basic",
            },
          ],
        },
      ],
      "/node/": [
        {
          text: "Node.js Backend Ecommerce",
          items: [
            {
              text: "Node 01: Kiếm tiền ở độ tuổi Lập Trình thanh xuân và lời nhắn nhủ?",
              link: "/node/node-01",
            },
          ],
        },
      ],
    },

    socialLinks: [{ icon: "github", link: "https://github.com/anonystick" }],
  },
});
