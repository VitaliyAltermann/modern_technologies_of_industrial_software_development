FROM node:19-alpine as build-stage
WORKDIR /app
COPY ./package*.json ./
RUN npm install
COPY . .
RUN npm run build

# этап production (production-stage)
#FROM nginx:1.24.0-alpine-slim as production-stage
#COPY nginx.conf /etc/nginx/nginx.conf
#COPY --from=build-stage /app/dist /usr/share/nginx/html
#EXPOSE 80 8080
#CMD ["nginx", "-g", "daemon off;"]
