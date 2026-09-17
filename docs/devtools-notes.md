# Week 8 Assignment 2: Chrome DevTools Inspection Notes

**Application**: Meridian Retail Bank — Account Dashboard  
**Branch**: `w8-css`  
**Inspected File**: `src/main/webapp/index.html` (with linked `style.css`)  

---

## 1. Network Tab Analysis

When loading and refreshing the Account Dashboard in the browser, the following resources are requested and loaded:

| Resource Name | Resource Type | HTTP Status Code | Transfer / Size | Notes |
|---|---|---|---|---|
| `index.html` | Document (`text/html`) | `200 OK` | ~3.2 KB | Root semantic HTML5 document |
| `style.css` | Stylesheet (`text/css`) | `200 OK` | ~3.8 KB | External stylesheet containing responsive layout and theme rules |

*Observation*: All assets load sequentially with HTTP 200 responses. CSS parsing occurs before initial paint, preventing any Flash of Unstyled Content (FOUC).

---

## 2. Computed CSS Property Inspection (Navigation Element)

Inspecting the `<nav>` and its child `<ul>` / `<a>` elements using the **Elements** $\rightarrow$ **Computed** pane in DevTools reveals the following applied properties:

### Target Element: `<nav>`
- **Computed Property**: `background-color`
  - **Computed Value**: `rgb(31, 56, 100)` (hex `#1F3864` — Savoira Corporate Navy)
  - **Cascade Source**: `style.css` line 31: `nav { background-color: #1F3864; }`
- **Computed Property**: `border-top`
  - **Computed Value**: `1px solid rgba(255, 255, 255, 0.15)`
  - **Cascade Source**: `style.css` line 32: `border-top: 1px solid rgba(255, 255, 255, 0.15);`

### Target Element: `<nav> <ul>`
- **Computed Property**: `display`
  - **Computed Value**: `flex`
  - **Cascade Source**: `style.css` line 36: `nav ul { display: flex; flex-direction: row; }`
- **Computed Property**: `flex-direction`
  - **Desktop / Tablet (> 600px)**: `row`
  - **Mobile ($\le$ 600px)**: `column` (overridden via media query)

### Target Element: `<nav> <ul> <li> <a href="...">`
- **Computed Property**: `color`
  - **Computed Value**: `rgb(255, 255, 255)` (`#FFFFFF`)
- **Computed Property**: `padding`
  - **Computed Value**: `14px 16px` (`padding-top: 14px; padding-bottom: 14px; padding-left: 16px; padding-right: 16px;`)
- **Hover State (`:hover`)**:
  - **Computed Value**: `background-color: rgb(15, 124, 124)` (`#0F7C7C` — Accent Teal)

---

## 3. Responsive Layout Verification

Using DevTools Device Mode (`Ctrl+Shift+M`), the layout was validated across the three target breakpoints:

1. **Full Width (Desktop > 900px, tested at 1200px)**:
   - `<main>` occupies 70% width, `<aside>` occupies 30% width side-by-side.
   - Navigation links arrange horizontally in a row.
2. **Tablet Breakpoint (tested at 800px)**:
   - The media query `@media (max-width: 900px)` maintains the side-by-side 70% / 30% distribution while reducing container padding to fit narrower screens.
3. **Mobile Breakpoint (tested at 500px)**:
   - The media query `@media (max-width: 600px)` activates:
     - `.layout-container` switches to `flex-direction: column`. `<main>` and `<aside>` stack vertically at 100% width.
     - `<nav> <ul>` switches to `flex-direction: column` for mobile-friendly full-width touch targets.
     - Table font-size drops to `13px` to maintain legible data density on compact screens.
