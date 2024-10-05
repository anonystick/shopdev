import { defineConfig, HeadConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "Shop Ecommerce Backend",
  description: "Con đường đi tới lập trình viên Backend",
  // header
  head: [
    ['meta', { name: 'keywords', content: 'java, golang, nodejs, backend, ecommerce' }],
    ['link', { rel: 'icon', href: '/favicon.ico' }]
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
      { text: 'Home', link: '/' },
      { text: 'Go Backend', link: '/golang' },
      { text: 'Java Backend', link: '/java' },
      { text: 'Nodejs Backend', link: '/node' }
    ],

    sidebar: {
      "/golang/": [
        {
          text: 'Golang Backend Ecommerce',
          items: [
            { text: 'Go Basic: Những kiến thức cơ bản về Go cần phải nắm', link: '/golang/go-24' },
            { text: 'Go 24: [User Login Interface] - Registration', link: '/golang/go-24' },
            { text: 'Go 25: [User Login Interface] - VerifyOTP', link: '/golang/go-25' },
            { text: 'Go 26: [User Login Interface] - Update Password Register', link: '/golang/go-26' },
            { text: 'Go 27: [User Login Interface] - Login and Token', link: '/golang/go-27' },
            // ... các mục khác
          ]
        }
      ],
      "/java/": [
        {
          text: 'Java Backend Ecommerce',
          items: [
            { text: 'Java 01: JAVA được phát triển như thế nào?', link: '/java/java-01' },
          ]
        }
      ],
      "/node/": [
        {
          text: 'Node.js Backend Ecommerce',
          items: [
            { text: 'Node 01: Kiếm tiền ở độ tuổi Lập Trình thanh xuân và lời nhắn nhủ?', link: '/node/node-01' },
          ]
        }
      ],
    },
    
    socialLinks: [
      { icon: 'github', link: 'https://github.com/anonystick' }
    ],
  }
})
