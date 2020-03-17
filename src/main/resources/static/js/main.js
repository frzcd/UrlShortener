
var urlApi = Vue.resource('/url{/link}');

Vue.component('long-to-short', {
    data: function() {
        return {
            text: ''
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Your url" v-model="text" />' +
            '<input type="button" value="Generate" @click="generate" />' +
        '</div>',
    methods: {
        generate: function () {
            var longUrl = {text: this.text};

            console.log(longUrl);
            urlApi.update({}, longUrl).then(result =>
                console.log(result)
            );
            this.text = ''
        }
    }
});

Vue.component('short-to-long', {
    data: function() {
        return {
            text: ''
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Your url" v-model="text" />' +
            '<input type="button" value="Send" @click="getLong" />' +
        '</div>',
    methods: {
        getLong: function () {
            var shortUrl = {text: this.text};
            console.log(shortUrl);
            urlApi.save({}, shortUrl).then(result =>
                console.log(result)
            );
            this.text = ''
        }
    }
});

Vue.component('message-row', {
    props: ['row'],
    template: '<div>{{ row.text }}</div>'
});

var app = new Vue({
    el: '#app',
    template: '<div>{{ message.text }}</div>',
    data: {
        message: ''
    },
    created: function () {
        urlApi.get().then(result =>
            this.message = result.body
        )
    }
});

var shortener = new Vue({
    el: '#shortener',
    template:
        '<div>' +
            '<div>Generate short link:</div>' +
            '<long-to-short />' +
            '<div></div>' +
            '<div>Get full url by short link:</div>' +
            '<short-to-long />' +
        '</div>'
});