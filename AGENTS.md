# AGENTS.md — FinFlow Enterprise Development Directives

## Agent Core Principles
1. **Inspect Before Modifying**: Never modify code without understanding domain boundaries.
2. **Preserve Architecture**: Maintain strict modular monolith boundaries (finflow-auth, finflow-expenses, finflow-bills, finflow-approvals, finflow-budgets, finflow-reporting).
3. **Zero Fake Data / Zero AI Clichés**: Use realistic enterprise financial structures (INR/USD, GSTIN/EIN, cost centers, GAAP/IFRS ledger principles).
4. **Zero Exposed Secrets**: Never commit real credentials, private keys, or API tokens.
5. **Deterministic Calculations**: Budget consumption, tax withholdings, currency conversions, and amortizations must be strictly deterministic with high-precision `BigDecimal`.
6. **Legitimate UI Iconography**: Use Lucide React or standard SVG systems. No emojis as functional buttons.
7. **Comprehensive Verification**: Compile, test, and audit every module prior to milestone sign-off.
