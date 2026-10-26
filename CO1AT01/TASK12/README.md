# Task 12 – Re-evaluate and Adapt the Design

## Aim
To evaluate and modify the original Campus Smart-Parking System to support new vehicle categories, new pricing rules, visitor passes and a larger parking facility.

## Problem Description
The university introduced new requirements for the Campus Smart-Parking System. The system must support e-bikes, delivery vans, one-day visitor passes and five floors with different numbers of parking slots.

I decided to modify the original design instead of rewriting the entire system. I introduced a pricing-rule interface so that different vehicle categories can use different fee calculations.

## A. Impact Analysis

The original system supported basic vehicle categories and hourly parking fees. The updated requirements introduce new vehicle categories, a flat fee for e-bikes, special delivery-van pricing and one-day visitor passes.

The original parking layout also needs to change because each floor can have a different number of slots.

The following parts of the design are affected:

- `ParkingFacility`: Updated to support five floors with different capacities.
- `PricingRule`: Introduced to separate fee calculation from parking management.
- `EBikePricing`: Calculates a flat parking fee for an e-bike.
- `DeliveryVanPricing`: Applies double the car rate during the specified daytime period.
- `OneDayPass`: Stores the visitor registration number and the day for which the pass is valid.
- `ParkingTicket`: Stores the vehicle details, entry time, allocated slot and pass information.

## B. Evaluation of the Original Design

The original design can be extended, but placing all pricing rules inside one large conditional statement would make it harder to maintain as more vehicle categories are introduced.

I used a `PricingRule` interface to separate pricing behaviour from the parking facility. Each pricing class implements its own fee calculation.

This approach makes it easier to add another pricing rule without rewriting the slot-allocation logic. The five-floor jagged array also supports different slot capacities on different floors.

## C. Adaptation and Implementation

### Five-floor parking facility
I used a jagged array to represent the parking facility. Each floor has a different number of slots:

- Floor 0: 3 slots
- Floor 1: 2 slots
- Floor 2: 4 slots
- Floor 3: 3 slots
- Floor 4: 5 slots

This makes the parking layout flexible.

### E-bike pricing
An e-bike pays a flat fee of Rs 25 per parking session, provided the parking duration is positive.

### Delivery-van pricing
A delivery van pays double the standard car rate during the period from 9 AM to 5 PM. The standard car rate in this demonstration is Rs 30 per started hour.

For simplicity, the program applies the rate based on the beginning of each started hourly billing block.

### One-day visitor pass
A visitor can use a one-day pass associated with their registration number and the relevant day. A valid pass makes the parking fee zero for that day's visit.

### Pricing-rule abstraction
The `PricingRule` interface defines a common `calculateFee()` method. The classes `StandardPricing`, `EBikePricing` and `DeliveryVanPricing` implement this method with their own pricing behaviour.

## D. Evidence-Based Defence

I chose to modify the original design because it already provided a useful foundation for vehicle records, tickets and parking-slot management.

The revised design supports new pricing behaviour through separate classes rather than putting every rule into one method. It also supports five floors with different slot capacities.

The following test cases demonstrate the updated requirements:

| Test case | Expected fee |
|---|---:|
| E-bike parked for a positive duration | Rs 25 |
| Delivery van parked from 10 AM to 12 PM | Rs 120 |
| Visitor using a valid one-day pass | Rs 0 |
| Car parked for 61 minutes | Rs 60 |
| Total collection for the test scenario | Rs 205 |

The total is calculated as Rs 25 + Rs 120 + Rs 0 + Rs 60 = Rs 205.

## How to Run
1. Open an online Java compiler.
2. Copy the complete code into the editor.
3. Ensure the main class is named `Main`.
4. Run the program.
5. Compare the actual output with the expected output.
6. Take a screenshot and save it as `output.png`.

## Alternative Design and Complexity Analysis

An alternative design would place all pricing conditions in a single `switch` statement. This may be straightforward for a small number of vehicle types, but every new category or pricing rule would require changes to the central method.

With a pricing-rule interface, each rule can be implemented separately. The fee calculation takes O(1) time for standard and flat-fee rules. Delivery-van calculation takes O(h) time, where h is the number of started hourly billing blocks.

Finding a free slot by scanning all floors and slots takes O(N) time in the worst case, where N is the total number of slots. The parking array requires O(N) space, and the active-ticket map requires O(V) space for V active vehicles.

## Result
I modified the Campus Smart-Parking System to support e-bikes, delivery vans, one-day visitor passes and a five-floor facility with different slot capacities. The program demonstrates a more extensible pricing design and produces a total collection of Rs 205 for the sample test scenario.
