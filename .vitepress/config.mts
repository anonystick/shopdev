import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  base: '/shopdev/',
  title: "Shop Ecommerce Backend",
  description: "For Backend",
  // header
  head: [["link", { rel: "icon", href: "/favicon.ico" }]],
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    // 
    logo: "/logo.svg",
    // outline
    outline: {
      level: [2, 6],
      label: "label",
    },
    // docFooter
    docFooter: {
      prev: "label previous",
      next: "label next",
    },
    darkModeSwitchLabel: "lightMNode",
    returnToTopLabel: "Up",
    search: {
      provider: "local",
    },
    footer: {
      message: "Released under the MIT License.",
      copyright: "Copyright © 2024-present Anonystick Golang",
    },
    lastUpdated: {
      text: "Updated at",
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

    sidebar: [
      {
        text: 'Golang Ecommerce',
        items: [
          { text: 'Markdown Examples', link: '/markdown-examples' },
          { text: 'Runtime API Examples', link: '/api-examples' }
        ]
      }
    ],

    socialLinks: [
      { icon: 'github', link: 'https://github.com/anonystick' }
    ],
  }
})
