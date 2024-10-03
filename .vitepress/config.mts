import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "Shop Ecommerce Backend",
  description: "Con đường đi tới lập trình viên Backend",
  // header
  head: [
    ['meta', { name: 'keywords', content: 'java, golang, nodejs, backend, ecommerce' }],
    ['meta', { name: 'description', content: 'Con đường đi tới lập trình viên Backend' }],
    ['meta', { property: 'og:title', content: 'Shop Ecommerce Backend' }],
    ['meta', { property: 'og:description', content: 'Triển khai một hệ thống thương mại từ a-z bao gồm các công nghệ backend như aws, rabbitmq, kafka, elasticsearch...' }],
    ['meta', { property: 'og:image', content: '/path-to-image.png' }],
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
      next: "bài iếp theo",
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
      { text: 'Go Backend', link: '/go-backend' },
      { text: 'Java Backend', link: '/java-backend' },
      { text: 'Nodejs Backend', link: '/nodejs-backend' }
    ],

    sidebar: {
      "/golang/": [
        {
          text: 'Golang Backend Ecommerce',
          items: [
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
            { text: 'Bài 1', link: '/java/bai1' },
            { text: 'Bài 2', link: '/java/bai2' },
            // ... các mục khác
          ]
        }
      ],
    },
    
    socialLinks: [
      { icon: 'github', link: 'https://github.com/anonystick' }
    ],
  }
})
