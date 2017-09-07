# Advisory Service

This REST service provides you with product information of the bank. It makes calculations for customers concearning an investment like buying a house. 

- `/{bankCode}/products` Products and interest rates
- `/calculators/affordabilityAnalysis` Analysis of the affordability for real estate based on assets, income, purchase price. Answers questions like: "What size of investment can I afford?" and "How much money do I need to buy that house/car/island/...?")
- `/calculators/financingProposal` Like the upper but makes proposals of products: fixed interest rates, Libor, etc. For better results, provide answers to the questionnaires from `/questionnaires/financingProposal`.
- `/calculators/rentalComparison` Like the upper but takes current rent into comparison.
- `/calculators/taxCalculation` Like the upper but additionally calculates change in taxes. You need to provide additional information like religious confession, and family situation.


For technical usage description please consult [SwaggerUI](https://api.raiffeisen.ch/loacalc/service/v4/apidocs/index.html).
