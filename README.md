```markdown
# Our_Framework — Project Summary & Architecture Diagram

Project: Our_Framework — Hybrid Test Automation Framework  
Tech stack: Java 11, Maven (multi-module), TestNG, Selenium WebDriver, WebDriverManager, RestAssured, ExtentReports, Log4j2, GitHub Actions

One‑line summary
I designed and delivered a production-ready, Maven multi‑module hybrid automation framework that runs scalable UI and API tests with thread-safe drivers and request handling, rich HTML reporting, and CI integration — enabling rapid onboarding and reliable test execution across local and CI environments.

Key responsibilities
- Architected the multi-module structure (common, api, ui) and centralized dependency/version management in the parent POM.
- Implemented reusable core services: ConfigManager, ExtentManager, TestListener, ScreenshotUtil.
- Built DriverFactory using WebDriverManager for cross-machine/CI driver management and remote/grid support.
- Implemented RestClient (per-thread RequestSpecification) with request/response logging and JSON schema support.
- Integrated ExtentReports and Log4j2 for actionable reporting and logs; wired CI (GitHub Actions) to run tests and upload artifacts.
- Authored docs, helper scripts, and example tests (demo targets: the-internet.herokuapp.com, reqres.in) for zero‑setup onboarding.

Benefits / Outcomes
- Eliminated manual driver management and driver-version mismatches via WebDriverManager.
- Enabled parallel, thread-safe test execution using ThreadLocal drivers and request specs.
- Reduced onboarding time: sample tests run out-of-the-box; helper script and docs provided.
- CI-ready: GitHub Actions runs both modules and uploads HTML reports and screenshots for rapid debugging.

How to run (quick)
- API tests: mvn -pl api test
- UI tests: mvn -pl ui test (set headless=false in ui/src/test/resources/config.properties to see browser)
- Run both: mvn -T 1C -am -pl api,ui test
- Reports: api/target/api-reports/extent-report.html and ui/target/ui-reports/extent-report.html

Architecture diagram (mermaid)
```mermaid
flowchart LR
  A[Developer / CI] -->|git push / run| B[GitHub Actions]
  subgraph Repo
    direction LR
    C[parent pom]
    C --> D[common]
    C --> E[api]
    C --> F[ui]
  end
  B -->|run tests| E
  B -->|run tests| F
  E --> G[RestClient (RestAssured)]
  E --> H[API Tests (reqres.in)]
  F --> I[DriverFactory (WebDriverManager)]
  F --> J[Page Objects & UI Tests (the-internet.herokuapp.com)]
  D --> K[ConfigManager]
  D --> L[ExtentManager / TestListener]
  D --> M[ScreenshotUtil / Log4j2]
  G --- L
  I --- L
  L --> N[ExtentReports HTML]
  B -->|upload artifacts| O[GitHub Artifacts (reports/screenshots/logs)]
```

One‑page layout notes (for PDF export)
- Top: Project title, tech stack, one‑line summary.
- Left column: Key responsibilities and benefits (bulleted).
- Right column: Quick run commands and architecture diagram (Mermaid).
- Footer: Report locations and CI pointer (.github/workflows/ci.yml).

Conversion to one‑page PDF
1) Recommended (using pandoc + mermaid-cli):
   - Install mermaid-cli (npm): npm i -g @mermaid-js/mermaid-cli
   - Render mermaid diagram to PNG:
       mmdc -i diagram.mmd -o diagram.png
     (or extract the mermaid block from this file into diagram.mmd)
   - Convert Markdown to PDF with pandoc:
       pandoc Project_Summary.md -o Our_Framework_Project_Summary.pdf --pdf-engine=wkhtmltopdf
     (or use wkhtmltopdf / weasyprint; ensure diagram.png is referenced in markdown)

2) Simpler (GitHub or VSCode):
   - Open Project_Summary.md in VSCode (with Mermaid preview extension), verify one-page layout, then print-to-PDF.
   - Or push to GitHub and use the “Print” (browser) option on the rendered Markdown, then Save as PDF.

Design tips for one‑page fit
- Use landscape orientation if diagram is wide.
- Reduce diagram size or simplify nodes to keep everything on one page.
- Use a single-column or two-column markdown template that maps to one PDF page.

Contact / repo
- Repo: https://github.com/SnakeEye15/Our_Framework
- Key files to reference: ui/src/main/java/.../DriverFactory.java, api/src/main/java/.../RestClient.java, common/src/main/java/.../reporting/ExtentManager.java, .github/workflows/ci.yml

```
